package controller.MapControllers;

import Settings.AnsiEscapeCodes;
import Settings.ShowMapSettings;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.MobileUnit;
import model.MapAsset.Tree;
import model.enums.CellType;
import utils.Vector2D;
import view.MapMenus.ShowMapMenu;
import view.enums.messages.MapMessage.ShowMapMessage;

public class ShowMapController {
    private final Map map;
    private final Vector2D center;

    public ShowMapController(Map map, Vector2D center) {
        this.map = map;
        this.center = center;
    }

    public void run() {
        if (!isCenterValid(center))
            return;
        ShowMapMenu showMapMenu = new ShowMapMenu(this);
        while (true) {
            if (showMapMenu.run().equals("exit")) return;
        }
    }

    public String showCellDetails(Vector2D cellCoordinate) {
        if (!map.isInMap(cellCoordinate))
            return ShowMapMessage.COORDINATE_OUT_OF_RANGE.getMessage();
        return map.getCell(cellCoordinate).toString();
    }

    public ShowMapMessage moveMap(Vector2D moveOffset) {
        if (moveOffset.y == 0 && moveOffset.x == 0)
            return ShowMapMessage.INVALID_MOVE_COMMAND;
        if (!isCenterValid(new Vector2D(center.x + moveOffset.x, center.y + moveOffset.y)))
            return ShowMapMessage.TARGET_OUT_OF_RANGE;
        center.x += moveOffset.x;
        center.y += moveOffset.y;
        return ShowMapMessage.MOVE_SUCCESSFUL;
    }

    public String printMap() {
        int yRange = ShowMapSettings.showMapHeightRange;
        int xRange = ShowMapSettings.showMapWidthRange;
        int cellWidth = ShowMapSettings.cellPrintCharWidth;
        int cellHeight = ShowMapSettings.cellPrintCharHeight;
        String output = "";
        String borderLine = "-".repeat((cellWidth + 1) * (2 * xRange + 1) + 1);
        for (int i = center.y - yRange; i <= center.y + yRange; i++) {
            output += borderLine;
            for (int l = 0; l < cellHeight; l++) {
                output += "\n|";
                for (int j = center.x - xRange; j <= center.x + xRange; j++) {
                    output += cellRowString(map.getCell(new Vector2D(j, i)), l);
                    output += '|';
                }
            }
            output += '\n';
        }
        output += borderLine;
        return output;
    }

    private String cellRowString(Cell cell, int rowNum) {
        StringBuilder str = new StringBuilder();
        int cellWidth = ShowMapSettings.cellPrintCharWidth;
        int cellHeight = ShowMapSettings.cellPrintCharHeight;
        //TODO add walls (W) support
        for (MapAsset asset : cell.getAllAssets()) {
            if (asset instanceof Building)
                str.append('B');
            if (asset instanceof MobileUnit)
                str.append('S');
            if (asset instanceof Tree)
                str.append('T');
        }
        str.append("#".repeat(Math.max(0, cellWidth * cellHeight - str.length())));
        String substring = str.substring(rowNum * cellWidth, (rowNum + 1) * cellWidth);
        return colorizeBasedOnCellType(substring, cell.getType());
    }

    private String colorizeBasedOnCellType(String str, CellType cellType) {
        String colorCode = cellType.getAsniColor();
        return colorCode + str + AnsiEscapeCodes.RESET;
    }

    private boolean isCenterValid(Vector2D coordinate) {
        Vector2D mapSize = map.getSize();
        int xRange = ShowMapSettings.showMapWidthRange;
        int yRange = ShowMapSettings.showMapHeightRange;
        return coordinate.x >= xRange && coordinate.y >= yRange &&
                coordinate.y + yRange < mapSize.y && coordinate.x + xRange < mapSize.x;
    }
}
