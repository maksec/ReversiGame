import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Reversi (game prod. by MaXeC)
                Выберите режим игры:
                 1. Игра против ИИ (лёгкий уровень)
                 2. Игра против ИИ (сложный уровень)
                 3. Играть вдвоём
                 4. Правила игры
                 5. Выход
                 Рекорд человека против ИИ: 0
                 """);
        Scanner in = new Scanner(System.in);
        int maxPoints = 0;
        int gameMode = in.nextInt();
        while (gameMode != 5) {
            if (gameMode < 1 || gameMode > 5) {
                System.out.println("Такого режима не существует =(");
            } else if (gameMode == 4) {
                System.out.println("""
                /    Правила игры Reversi (game prod. by MaXeC):
                /    Чтобы сделать ход укажите сначала строку, а потом столбец
                /    клетки, куда вы хотите сходить в формате "1 2"
                /    Был реализован весь доп функционал игры:
                /       - лёгкий бот
                /       - сложный бот
                /       - игра вдвоём
                /       - возврат хода (на сколько угодно назад)
                /       - показ всех доступных для хода полей (визуализация)
                /       - вывод максимального результата против ИИ за сессию
                /       - добавлено красивое меню и пояснения к действиям во время игры
                /    Приятной игры! Надеюсь, вам понравится!
                """);
            } else {
                if (gameMode == 3) {
                    Game game = new Game(gameMode);
                    while (game.gameStatus_ != 1) {
                        int i = 0, j = 0;
                        if (game.checkCanMakeMove(1)) {
                            game.printBoard(1);
                            System.out.println("Ход первого игрока! (X)\nВведите 0 если хотите вернуть ход!");
                            i = in.nextInt();
                            if (i == 0) {
                                game.makeBackMove();
                                continue;
                            }
                            j = in.nextInt();
                            while(!game.makeMove(i, j, 1)) {
                                game.printBoard(1);
                                System.out.println("Ход первого игрока! (X)\nВведите 0 если хотите вернуть ход!");
                                i = in.nextInt();
                                if (i == 0) {
                                    game.makeBackMove();
                                    continue;
                                }
                                j = in.nextInt();
                            }
                        }
                        if (game.gameStatus_ == 1) {
                            break;
                        }
                        if (game.checkCanMakeMove(2)) {
                            game.printBoard(2);
                            System.out.println("Ход второго игрока! (0)\nВведите 0 если хотите вернуть ход!");
                            i = in.nextInt();
                            j = 0;
                            if (i == 0) {
                                game.makeBackMove();
                            } else {
                                j = in.nextInt();
                            }
                            while(!game.makeMove(i, j, 2)) {
                                game.printBoard(2);
                                System.out.println("Ход второго игрока! (0)\nВведите 0 если хотите вернуть ход!");
                                i = in.nextInt();
                                if (i == 0) {
                                    game.makeBackMove();
                                    continue;
                                }
                                j = in.nextInt();
                            }
                        }
                    }
                    game.printBoard(1);
                    System.out.println("Игра окончена!");
                    game.printScore();
                } else if (gameMode == 1) {
                    Game game = new Game(gameMode);
                    System.out.println("Введите 1 если хотите ходить первым, 2 если вторым:");
                    int num = in.nextInt();
                    if (num == 1) {
                        while (game.gameStatus_ != 1) {
                            game.printBoard(1);
                            System.out.println("Ваш ход! (X)\nВведите 0 если хотите вернуть ход!");
                            int i = in.nextInt();
                            if (i == 0) {
                                game.makeBackMove();
                                continue;
                            }
                            int j = in.nextInt();
                            while(!game.makeMove(i, j, 1)) {
                                game.printBoard(1);
                                System.out.println("Ваш ход! (X)\nВведите 0 если хотите вернуть ход!");
                                i = in.nextInt();
                                if (i == 0) {
                                    game.makeBackMove();
                                    continue;
                                }
                                j = in.nextInt();
                            }
                            if (game.gameStatus_ == 1) {
                                break;
                            }
                            game.easyBotMove(2);
                        }
                    } else {
                        game.makeMove(3, 5, 1);
                        while (game.gameStatus_ != 1) {
                            game.printBoard(2);
                            System.out.println("Ваш ход! (0)\nВведите 0 если хотите вернуть ход!");
                            int i = in.nextInt();
                            if (i == 0) {
                                game.makeBackMove();
                                continue;
                            }
                            int j = in.nextInt();
                            while(!game.makeMove(i, j, 2)) {
                                game.printBoard(2);
                                System.out.println("Ваш ход! (0)\nВведите 0 если хотите вернуть ход!");
                                i = in.nextInt();
                                if (i == 0) {
                                    game.makeBackMove();
                                    continue;
                                }
                                j = in.nextInt();
                            }
                            if (game.gameStatus_ == 1) {
                                break;
                            }
                            game.easyBotMove(1);
                        }
                    }
                    game.printBoard(1);
                    System.out.println("Игра окончена!");
                    if (maxPoints < game.firstPlayerScore_) {
                        maxPoints = game.firstPlayerScore_;
                    }
                } else {
                    Game game = new Game(gameMode);
                    System.out.println("Введите 1 если хотите ходить первым, 2 если вторым:");
                    int num = in.nextInt();
                    if (num == 1) {
                        while (game.gameStatus_ != 1) {
                            game.printBoard(1);
                            System.out.println("Ваш ход! (X)\nВведите 0 если хотите вернуть ход!");
                            int i = in.nextInt();
                            if (i == 0) {
                                game.makeBackMove();
                                continue;
                            }
                            int j = in.nextInt();
                            while(!game.makeMove(i, j, 1)) {
                                game.printBoard(1);
                                System.out.println("Ваш ход! (X)\nВведите 0 если хотите вернуть ход!");
                                i = in.nextInt();
                                if (i == 0) {
                                    game.makeBackMove();
                                    continue;
                                }
                                j = in.nextInt();
                            }
                            if (game.gameStatus_ == 1) {
                                break;
                            }
                            game.hardBotMove(2);
                        }
                    } else {
                        game.makeMove(3, 5, 1);
                        while (game.gameStatus_ != 1) {
                            game.printBoard(2);
                            System.out.println("Ваш ход! (0)\nВведите 0 если хотите вернуть ход!");
                            int i = in.nextInt();
                            if (i == 0) {
                                game.makeBackMove();
                                continue;
                            }
                            int j = in.nextInt();
                            while(!game.makeMove(i, j, 2)) {
                                game.printBoard(2);
                                System.out.println("Ваш ход! (0)\nВведите 0 если хотите вернуть ход!");
                                i = in.nextInt();
                                if (i == 0) {
                                    game.makeBackMove();
                                    continue;
                                }
                                j = in.nextInt();
                            }
                            if (game.gameStatus_ == 1) {
                                break;
                            }
                            game.hardBotMove(1);
                        }
                    }
                    game.printBoard(1);
                    System.out.println("Игра окончена!");
                    if (maxPoints < game.firstPlayerScore_) {
                        maxPoints = game.firstPlayerScore_;
                    }
                }
            }
            System.out.println("""
                
                
                
                Reversi (game prod. by MaXeC)
                Выберите режим игры:
                 1. Игра против ИИ (лёгкий уровень)
                 2. Игра против ИИ (сложный уровень)
                 3. Играть вдвоём
                 4. Правила игры
                 5. Выход
                 Рекорд человека против ИИ:""" + " " + maxPoints + '\n');
            gameMode = in.nextInt();
        }
        System.out.println("Спасибо, что поиграли! До встречи!");
    }
}