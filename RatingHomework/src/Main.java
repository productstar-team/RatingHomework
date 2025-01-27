import java.util.List;

public class Main {
    public static void main(String[] args) {
        RankingSystem rankingSystem = new RankingSystem();

        // Добавление игроков
        rankingSystem.addPlayer(new Player(1, "Alice", 1500));
        rankingSystem.addPlayer(new Player(2, "Bob", 1400));
        rankingSystem.addPlayer(new Player(3, "Charlie", 1550));

        // Вывод топ-3 игроков
        System.out.println("\nТоп 3 игрока:");
        List<Player> topPlayers = rankingSystem.getTopPlayers(3);
        for (int i = 0; i < topPlayers.size(); i++) {
            System.out.println((i + 1) + ". " + topPlayers.get(i).getName() + " - " + topPlayers.get(i).getRating());
        }

        // Обновление рейтинга игрока
        rankingSystem.updatePlayerRating(2, 1600);

        // Вывод обновленного топ-3 игроков
        System.out.println("\nТоп 3 игрока после обновления:");
        topPlayers = rankingSystem.getTopPlayers(3);
        for (int i = 0; i < topPlayers.size(); i++) {
            System.out.println((i + 1) + ". " + topPlayers.get(i).getName() + " - " + topPlayers.get(i).getRating());
        }

        // Получение ранга игрока
        try {
            int aliceRank = rankingSystem.getPlayerRank(1);
            System.out.println("\nТекущий ранг Alice: " + aliceRank);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Попытка получить ранг несуществующего игрока
        System.out.println("\nПопытка получить ранг несуществующего игрока:");
        try {
            rankingSystem.getPlayerRank(4);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}