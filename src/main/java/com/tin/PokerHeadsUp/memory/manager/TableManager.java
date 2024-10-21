package com.tin.PokerHeadsUp.memory.manager;

import com.tin.PokerHeadsUp.memory.model.Player;
import com.tin.PokerHeadsUp.memory.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.tin.PokerHeadsUp.config.PokerConstants.MAX_PLAYERS;
import static com.tin.PokerHeadsUp.config.PokerConstants.MAX_TABLES;

public class TableManager {

    // map of active tables, <tableId, Table>
    private final Map<Long, Table> tables;

    // map of players to tables they are sitting at <playerId, tableId>
    private final Map<Player, List<Long>> playerLocations;

    // queue of waiting players
    protected final Queue<Player> waitingPlayers;

    public TableManager() {
        tables = new ConcurrentHashMap<>();
        waitingPlayers = new ConcurrentLinkedQueue<>();
        playerLocations = new ConcurrentHashMap<>();
    }

    public Table joinTable(Player player) throws Exception {
        if (!eligibleToJoinNewTable(player.getPlayerId())) throw new Exception("Attempting to sit at too many tables");

        // try to find a table with a spot
        for (Table table : tables.values()) {
            if (table.getPlayerList().size() < MAX_PLAYERS) {
                return assignTable(player, table);
            }
        }

        // create new table if no space
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        Table newTable = new Table(playerList);
        tables.put(newTable.getTableId(), newTable);
        List<Long> locations = playerLocations.getOrDefault(player, new ArrayList<>());
        locations.add(newTable.getTableId());
        playerLocations.put(player, locations);
        return newTable;
    }

    public Table assignTable(Player player, Table table) {
        List<Player> playerList = table.getPlayerList();
        playerList.add(player);
        return table;
    }

    public boolean eligibleToJoinNewTable(Long playerId) {
        // check if player is sitting at a table (TODO: using max table to implement multi tabling in the future)
        int count = 0;
        for (Table table : tables.values()) {
            if (table.getPlayerList().stream()
                    .anyMatch(p -> p.getPlayerId().equals(playerId))) {
                // If player is at this table, inc count
                count++;
                if (count >= MAX_TABLES); {
                    return false;
                }
            }
        }
        return true;
    }
}
