//package database;
//
//import model.Lobby;
//
//public class LobbyRemover extends Thread{
//    @Override
//    public void run() {
//        while (true){
//            for (Lobby lobby : Database.getInstance().getAllLobbies()) {
//                if(lobby.isExpired()){
//                    System.out.println("Lobby remove: " + lobby.getId());
//                    Database.getInstance().removeLobby(lobby.getId());
//                }
//                try {
//                    sleep(10000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
//}
