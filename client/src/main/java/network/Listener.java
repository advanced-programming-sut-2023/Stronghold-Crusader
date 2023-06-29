package network;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Listener extends Thread {
    private final DataInputStream dataInputStream;
    private static LinkedBlockingQueue<String> lastContent = new LinkedBlockingQueue<>();

    private enum InputRegex {
        CHAT_UPDATE("chat_updated:(?<request>\\S+)");
        private final String regex;

        InputRegex(String regex) {
            this.regex = regex;
        }

        public static InputRegex getRegex(String date) {
            for (InputRegex inputRegex : InputRegex.values()) {
                if (date.matches(inputRegex.regex)) return inputRegex;
            }
            return null;
        }
    }

    public Listener(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                String data = dataInputStream.readUTF();
                InputRegex input = InputRegex.getRegex(data);
                if (input == null) {
                    lastContent.put(data);
                    continue;
                }
                switch (input) {
                    case CHAT_UPDATE:
                        chatUpdate(data);
                        break;
                }
            }
        } catch (SocketException socketException) {
            return;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void chatUpdate(String input) {
        Pattern pattern = Pattern.compile(InputRegex.CHAT_UPDATE.regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        String requestStr = matcher.group("request");
        Request request = new Gson().fromJson(requestStr, Request.class);
    }

    public static String consumeLastInput() throws InterruptedException {
        return lastContent.take();
    }
}
