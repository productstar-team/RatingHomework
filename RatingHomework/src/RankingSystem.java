import java.util.*;

public class RankingSystem {
    private final TreeMap<Integer, Set<Player>> playerRankings;
    private final Map<Integer, Player> playerById;

    public RankingSystem() {
        playerRankings = new TreeMap<>(Collections.reverseOrder());
        playerById = new HashMap<>();
    }

    public void addPlayer(Player player) {
        playerRankings.computeIfAbsent(player.getRating(), k -> new HashSet<>()).add(player);
        playerById.put(player.getId(), player);
        System.out.println("Добавлен игрок: " + player);
    }

    public void updatePlayerRating(int playerId, int newRating) {
        Player player = playerById.get(playerId);
        if (player == null) {
            throw new IllegalArgumentException("Игрок с ID " + playerId + " не найден в системе");
        }

        playerRankings.get(player.getRating()).remove(player);
        if (playerRankings.get(player.getRating()).isEmpty()) {
            playerRankings.remove(player.getRating());
        }

        player.setRating(newRating);
        playerRankings.computeIfAbsent(newRating, k -> new HashSet<>()).add(player);
        System.out.println("Обновлен рейтинг игрока " + player.getName() + ": " + newRating);
    }

    public List<Player> getTopPlayers(int n) {
        List<Player> topPlayers = new ArrayList<>();
        for (Set<Player> players : playerRankings.values()) {
            topPlayers.addAll(players);
            if (topPlayers.size() >= n) {
                break;
            }
        }
        return topPlayers.subList(0, Math.min(n, topPlayers.size()));
    }

    public int getPlayerRank(int playerId) {
        Player player = playerById.get(playerId);
        if (player == null) {
            throw new IllegalArgumentException("Игрок с ID " + playerId + " не найден в системе");
        }

        int rank = 1;
        for (Map.Entry<Integer, Set<Player>> entry : playerRankings.entrySet()) {
            if (entry.getKey() > player.getRating()) {
                rank += entry.getValue().size();
            } else if (entry.getKey().equals(player.getRating())) {
                for (Player p : entry.getValue()) {
                    if (p.getId() == playerId) {
                        return rank;
                    }
                    rank++;
                }
            } else {
                break;
            }
        }
        return rank;
    }
}