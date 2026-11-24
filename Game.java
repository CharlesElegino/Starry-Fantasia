import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Random;
import javax.sound.sampled.*;

class Game extends JFrame {
    final MainMenuScreen mainMenuScreen = new MainMenuScreen();
    final CharacterSelectionScreen characterSelectionScreen = new CharacterSelectionScreen();
    final CharacterMenuScreen characterMenuScreen = new CharacterMenuScreen();
    final StageSelectionScreen stageSelectionScreen = new StageSelectionScreen();
    final BattleScreen battleScreen = new BattleScreen();
    CharacterStatus characterStats = new CharacterStatus();
    EnemyStatus enemyStats = new EnemyStatus();
    PlayerStats playerStats = new PlayerStats();
    int skillPoints = 10;
    int playerBuffCD = 0;
    Random random = new Random();
    int enemyBuffCD = random.nextInt(6);
    float playerDMG;
    float playerDMG2;
    float playerATKBUFF;
    float playerDEFBUFF;
    float ultDMG;
    int ULTIMATE_SKILL_POINT_COST = 5;
    int action;
    float enemyDMG;
    float enemyATKBUFF;
    float enemyDEFBUFF;
    boolean playerDefeated = false;
    boolean enemyDefeated = false;

    //Sounds
    File BGMPath = new File("resource\\Sounds\\BGM\\main-bgm.wav");
    File hoverPath = new File("resource\\Sounds\\SFX\\hover-sfx.wav");
    File selectPath = new File("resource\\Sounds\\SFX\\press-sfx.wav");
    File SBGM1 = new File("resource\\Sounds\\BGM\\stage-1-bgm.wav");
    File SBGM2 = new File("resource\\Sounds\\BGM\\stage-2-bgm.wav");
    File SBGM3 = new File("resource\\Sounds\\BGM\\stage-3-bgm.wav");

    private AudioInputStream AIS;
    private Clip clip;
    private Clip musicClip;

    /** Load an image from the project's resource directory or classpath. */
    private ImageIcon loadIcon(String relativePath) {
        String resourcePath = "/resource/" + relativePath.replace('\\', '/');
        java.net.URL resource = getClass().getResource(resourcePath);
        if (resource != null) {
            return new ImageIcon(resource);
        }

        File imageFile = new File("resource", relativePath);
        if (imageFile.isFile()) {
            return new ImageIcon(imageFile.getPath());
        }
        throw new IllegalArgumentException("Image resource not found: " + resourcePath);
    }

    /** Configure the window and build its initial screen before displaying it. */
    void initialize() {
        final int width = 960;
        final int height = 540;
        setTitle("STARRY FANTASIA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        buttonActionListener();
        setIconImage(icon.getImage());
        showMainMenu();
        setVisible(true);
    }

    // MAIN ICONS, LABELS, BUTTONS ETC.
    ImageIcon mainBG = new ImageIcon(loadIcon("Backgrounds\\Main.gif").getImage().getScaledInstance(960, 540, Image.SCALE_DEFAULT));
    ImageIcon play = new ImageIcon(loadIcon("UI\\Buttons\\play.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon playPressed = new ImageIcon(loadIcon("UI\\Buttons\\play2.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon continue1 = new ImageIcon(loadIcon("UI\\Buttons\\continue.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon continuePressed = new ImageIcon(loadIcon("UI\\Buttons\\continue2.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon quit = new ImageIcon(loadIcon("UI\\Buttons\\quit.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon quitPressed = new ImageIcon(loadIcon("UI\\Buttons\\quit2.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    JLabel mainDisplay = new JLabel(mainBG);
    JButton playBTN = new JButton(play);
    JButton continueBTN = new JButton(continue1);
    JButton quitBTN = new JButton(quit);
    JPanel mainPanel = new JPanel();

    void showMainMenu() { mainMenuScreen.build(this); }

    // LOBBY PANELS, LABELS, BUTTONS ETC.
    ImageIcon lobbybg = new ImageIcon(loadIcon("Backgrounds\\lobby.png").getImage().getScaledInstance(960, 540, Image.SCALE_DEFAULT));
    ImageIcon swordsmanIdleIcon = loadIcon("Characters\\Players\\Swordsman\\idle.gif");
    ImageIcon rangerIdleIcon = loadIcon("Characters\\Players\\Ranger\\idle.gif");
    ImageIcon magusIdleIcon = loadIcon("Characters\\Players\\Magus\\idle.gif");
    ImageIcon back = new ImageIcon(loadIcon("UI\\Buttons\\back.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon backPressed = new ImageIcon(loadIcon("UI\\Buttons\\back2.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon confirm = new ImageIcon(loadIcon("UI\\Buttons\\confirm.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon confirmPressed = new ImageIcon(loadIcon("UI\\Buttons\\confirm2.png").getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
    ImageIcon icon = new ImageIcon(loadIcon("UI\\Game Icon.png").getImage().getScaledInstance(150, 132, Image.SCALE_DEFAULT));
    ImageIcon red = new ImageIcon(loadIcon("UI\\redPanel.png").getImage().getScaledInstance(236, 300, Image.SCALE_DEFAULT));
    ImageIcon green = new ImageIcon(loadIcon("UI\\greenPanel.png").getImage().getScaledInstance(236, 300, Image.SCALE_DEFAULT));
    ImageIcon blue = new ImageIcon(loadIcon("UI\\bluePanel.png").getImage().getScaledInstance(236, 300, Image.SCALE_DEFAULT));
    ImageIcon book = new ImageIcon(loadIcon("UI\\book.png").getImage().getScaledInstance(748, 488, Image.SCALE_DEFAULT));
    ImageIcon melee = new ImageIcon(loadIcon("UI\\Swordsman.png").getImage().getScaledInstance(308, 96, Image.SCALE_DEFAULT));
    ImageIcon melee2 = new ImageIcon(loadIcon("UI\\Swordsman2.png").getImage().getScaledInstance(308, 96, Image.SCALE_DEFAULT));
    ImageIcon range = new ImageIcon(loadIcon("UI\\Ranger.png").getImage().getScaledInstance(308, 96, Image.SCALE_DEFAULT));
    ImageIcon range2 = new ImageIcon(loadIcon("UI\\Ranger2.png").getImage().getScaledInstance(308, 96, Image.SCALE_DEFAULT));
    ImageIcon magic = new ImageIcon(loadIcon("UI\\Magus.png").getImage().getScaledInstance(308, 96, Image.SCALE_DEFAULT));
    ImageIcon magic2 = new ImageIcon(loadIcon("UI\\Magus2.png").getImage().getScaledInstance(308, 96, Image.SCALE_DEFAULT));
    ImageIcon gametitle = loadIcon("UI\\game title.png");
    JLabel gameTitleDisplay = new JLabel(gametitle);
    JLabel portrait = new JLabel(melee2);
    JLabel portrait2 = new JLabel(range);
    JLabel portrait3 = new JLabel(magic);
    JLabel panelDisplay = new JLabel();
    JLabel bookDisplay = new JLabel(book);
    JTextField name = new JTextField();
    JLabel info = new JLabel("CLASS INFO");
    JLabel classificationDisplay = new JLabel("CLASS: ");
    JLabel HPDisplay = new JLabel("HP: ");
    JLabel ATKDisplay = new JLabel("ATK: ");
    JLabel DEFDisplay = new JLabel("DEF: ");
    JLabel nameDisplay = new JLabel("NAME: ");
    JLabel classWrite = new JLabel();
    JLabel hpWrite = new JLabel();
    JLabel atkWrite = new JLabel();
    JLabel defWrite = new JLabel();
    JButton backBTN = new JButton(back);
    JButton confirmBTN = new JButton(confirm);
    JLabel classDisplay = new JLabel();
    JLabel lobbyDisplay = new JLabel(lobbybg);
    JPanel lobbyPanel = new JPanel();
    Random rand = new Random();

    void showCharacterSelection() { characterSelectionScreen.build(this); }

    // REST/SAFE ZONE ICONS, LABELS, BUTTONS ETC.
    ImageIcon safeZoneBG = new ImageIcon(loadIcon("Backgrounds\\training.gif").getImage().getScaledInstance(960, 540, Image.SCALE_DEFAULT));
    ImageIcon battle = new ImageIcon(loadIcon("UI\\Buttons\\battle.png").getImage().getScaledInstance(111, 52, Image.SCALE_DEFAULT));
    ImageIcon save = new ImageIcon(loadIcon("UI\\Buttons\\save.png").getImage().getScaledInstance(111, 52, Image.SCALE_DEFAULT));
    ImageIcon exit = new ImageIcon(loadIcon("UI\\Buttons\\exit.png").getImage().getScaledInstance(111, 52, Image.SCALE_DEFAULT));
    JLabel restDisplay = new JLabel(safeZoneBG);
    JPanel safeZonePanel = new JPanel();
    JPanel statPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JButton battleBTN = new JButton(battle);
    JButton saveBTN = new JButton(save);
    JButton exitBTN = new JButton(exit);
    JLabel info2 = new JLabel("ADVENTURER STATUS", SwingConstants.CENTER);
    JLabel actionHeader = new JLabel("ADVENTURE", SwingConstants.CENTER);
    JLabel nameDisplay2 = new JLabel("NAME: ");
    JLabel classDisplay2 = new JLabel("CLASS: ");
    JLabel HPDisplay2 = new JLabel("HP: ");
    JLabel ATKDisplay2 = new JLabel("ATK: ");
    JLabel DEFDisplay2 = new JLabel("DEF: ");
    JLabel levelDisplay = new JLabel("LEVEL: ");
    JLabel experienceDisplay = new JLabel("EXP: ");
    JLabel usernameWrite = new JLabel("username");
    JLabel classWrite2 = new JLabel("class");
    JLabel hpWrite2 = new JLabel("hp");
    JLabel atkWrite2 = new JLabel("atk");
    JLabel defWrite2 = new JLabel("def");
    JLabel levelWrite = new JLabel("1");
    JLabel experienceWrite = new JLabel("0 / 100");
    JProgressBar experienceBar = new JProgressBar();

    void showCharacterMenu() { characterMenuScreen.build(this); }

    ImageIcon dungeonBG = new ImageIcon(loadIcon("Backgrounds\\Journey.gif").getImage().getScaledInstance(960, 540, Image.SCALE_DEFAULT));
    ImageIcon stageBG1 = new ImageIcon(loadIcon("Backgrounds\\S1.gif").getImage().getScaledInstance(960, 540, Image.SCALE_DEFAULT));
    ImageIcon stageBG2 = new ImageIcon(loadIcon("Backgrounds\\S2.gif").getImage().getScaledInstance(960, 540, Image.SCALE_DEFAULT));
    ImageIcon stageBG3 = new ImageIcon(loadIcon("Backgrounds\\S3.gif").getImage().getScaledInstance(960, 540, Image.SCALE_DEFAULT));
    ImageIcon S1CON = loadIcon("UI\\S1ICON.png");
    ImageIcon S2CON = loadIcon("UI\\S2ICON.png");
    ImageIcon S3CON = loadIcon("UI\\S3ICON.png");
    JLabel StageBG = new JLabel(stageBG1);
    JLabel dungeonBGDisplay = new JLabel(dungeonBG);
    JPanel dungeonPanel = new JPanel();
    JButton stage1BTN = new JButton(S1CON);
    JButton stage2BTN = new JButton(S2CON);
    JButton stage3BTN = new JButton(S3CON);
    JButton backBTN2 = new JButton(back);
    JPanel QWERTY = new JPanel();
    JLabel stageSelectionTitle = new JLabel("CHOOSE YOUR DESTINATION", SwingConstants.CENTER);
    JLabel stage1Caption = new JLabel("STAGE 1", SwingConstants.CENTER);
    JLabel stage2Caption = new JLabel("STAGE 2", SwingConstants.CENTER);
    JLabel stage3Caption = new JLabel("STAGE 3", SwingConstants.CENTER);

    void showStageSelection() { stageSelectionScreen.build(this); }

    //BATTLE UI ICONS, LABELS, BUTTONS ETC.
    ImageIcon fight = new ImageIcon(loadIcon("UI\\Buttons\\fight.png").getImage().getScaledInstance(102, 34, Image.SCALE_DEFAULT));
    ImageIcon fightPressed = new ImageIcon(loadIcon("UI\\Buttons\\fight2.png").getImage().getScaledInstance(102, 34, Image.SCALE_DEFAULT));
    ImageIcon run = new ImageIcon(loadIcon("UI\\Buttons\\run.png").getImage().getScaledInstance(102, 34, Image.SCALE_DEFAULT));
    ImageIcon runPressed = new ImageIcon(loadIcon("UI\\Buttons\\run2.png").getImage().getScaledInstance(102, 34, Image.SCALE_DEFAULT));
    ImageIcon atk = new ImageIcon(loadIcon("UI\\Buttons\\atk.png").getImage().getScaledInstance(152, 41, Image.SCALE_DEFAULT));
    ImageIcon atkPressd = new ImageIcon(loadIcon("UI\\Buttons\\atk2.png").getImage().getScaledInstance(152, 41, Image.SCALE_DEFAULT));
    ImageIcon atk2 = new ImageIcon(loadIcon("UI\\Buttons\\atk3.png").getImage().getScaledInstance(152, 41, Image.SCALE_DEFAULT));
    ImageIcon atk2Pressed = new ImageIcon(loadIcon("UI\\Buttons\\atk4.png").getImage().getScaledInstance(152, 41, Image.SCALE_DEFAULT));
    ImageIcon buff = new ImageIcon(loadIcon("UI\\Buttons\\buff.png").getImage().getScaledInstance(152, 41, Image.SCALE_DEFAULT));
    ImageIcon buffPressed = new ImageIcon(loadIcon("UI\\Buttons\\buff2.png").getImage().getScaledInstance(152, 41, Image.SCALE_DEFAULT));
    ImageIcon ult = new ImageIcon(loadIcon("UI\\Buttons\\ult.png").getImage().getScaledInstance(152, 41, Image.SCALE_DEFAULT));
    ImageIcon ultPressed = new ImageIcon(loadIcon("UI\\Buttons\\ult2.png").getImage().getScaledInstance(152, 41, Image.SCALE_DEFAULT));
    JPanel upperPanel = new JPanel();
    JPanel lowerPanel = new JPanel();
    JPanel skillsPanel = new JPanel();
    JPanel battleLogPanel = new JPanel();
    JButton fightBTN = new JButton(fight);
    JButton runBTN = new JButton(run);
    JButton skill1BTN = new JButton(atk);
    JButton skill2BTN = new JButton(atk2);
    JButton buffBTN = new JButton(buff);
    JButton ultBTN = new JButton(ult);
    // SWORDSMAN ANIMATIONS
    ImageIcon swordsmanIdle = new ImageIcon(loadIcon("Characters\\Players\\Swordsman\\idle 2.gif").getImage().getScaledInstance(227, 212, Image.SCALE_DEFAULT));
    ImageIcon swordsmanAtk = new ImageIcon(loadIcon("Characters\\Players\\Swordsman\\atk.gif").getImage().getScaledInstance(938, 357, Image.SCALE_DEFAULT));
    ImageIcon swordsmanAtk2 = new ImageIcon(loadIcon("Characters\\Players\\Swordsman\\atk 2.gif").getImage().getScaledInstance(938, 357, Image.SCALE_DEFAULT));
    ImageIcon swordsmanBuff = new ImageIcon(loadIcon("Characters\\Players\\Swordsman\\buff.gif").getImage().getScaledInstance(408, 492, Image.SCALE_DEFAULT));
    ImageIcon swordsmanUlt = new ImageIcon(loadIcon("Characters\\Players\\Swordsman\\ult.gif").getImage().getScaledInstance(960, 712, Image.SCALE_DEFAULT));
    ImageIcon swordsmanIcon = new ImageIcon(loadIcon("Characters\\Players\\Swordsman\\icon.png").getImage().getScaledInstance(56, 38, Image.SCALE_DEFAULT));
    // RANGER ANIMATIONS
    ImageIcon rangerIdle = new ImageIcon(loadIcon("Characters\\Players\\Ranger\\idle.gif").getImage().getScaledInstance(227, 212, Image.SCALE_DEFAULT));
    ImageIcon rangerAtk = new ImageIcon(loadIcon("Characters\\Players\\Ranger\\atk.gif").getImage().getScaledInstance(672, 324, Image.SCALE_DEFAULT));
    ImageIcon rangerAtk2 = new ImageIcon(loadIcon("Characters\\Players\\Ranger\\atk 2.gif").getImage().getScaledInstance(672, 324, Image.SCALE_DEFAULT));
    ImageIcon rangerBuff = new ImageIcon(loadIcon("Characters\\Players\\Ranger\\buff.gif").getImage().getScaledInstance(140, 204, Image.SCALE_DEFAULT));
    ImageIcon rangerUlt = new ImageIcon(loadIcon("Characters\\Players\\Ranger\\ult.gif").getImage().getScaledInstance(660, 466, Image.SCALE_DEFAULT));
    ImageIcon rangerIcon = new ImageIcon(loadIcon("Characters\\Players\\Ranger\\icon.png").getImage().getScaledInstance(56, 38, Image.SCALE_DEFAULT));
    // MAGUS ANIMATIONS
    ImageIcon magusidle = new ImageIcon(loadIcon("Characters\\Players\\Magus\\idle.gif").getImage().getScaledInstance(227, 212, Image.SCALE_DEFAULT));
    ImageIcon magusAtk = new ImageIcon(loadIcon("Characters\\Players\\Magus\\atk.gif").getImage().getScaledInstance(688, 495, Image.SCALE_DEFAULT));
    ImageIcon magusAtk2 = new ImageIcon(loadIcon("Characters\\Players\\Magus\\atk 2.gif").getImage().getScaledInstance(615, 256, Image.SCALE_DEFAULT));
    // This asset set has no Magus buff animation, so reuse the idle sprite.
    ImageIcon magusBuff = magusidle;
    ImageIcon magusUlt = new ImageIcon(loadIcon("Characters\\Players\\Magus\\ult.gif").getImage().getScaledInstance(1813, 493, Image.SCALE_DEFAULT));
    ImageIcon magusIcon = new ImageIcon(loadIcon("Characters\\Players\\Magus\\Icon.png").getImage().getScaledInstance(56, 38, Image.SCALE_DEFAULT));
    ImageIcon color = loadIcon("Concept design\\color.png");
    JLabel charHolder = new JLabel();
    // ENEMY 1
    ImageIcon enemy1idle = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 1\\idle.gif").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT));
    ImageIcon enemyAttack = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 1\\atk.gif").getImage().getScaledInstance(291, 336, Image.SCALE_DEFAULT));
    ImageIcon enemy1buff = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 1\\buff.gif").getImage().getScaledInstance(144, 169, Image.SCALE_DEFAULT));
    ImageIcon enemy1Icon = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 1\\icon.png").getImage().getScaledInstance(56, 38, Image.SCALE_DEFAULT));
    // ENEMY 2
    ImageIcon enemy2idle = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 2\\idle.gif").getImage().getScaledInstance(180, 220, Image.SCALE_DEFAULT));
    ImageIcon enemy2atk = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 2\\atk.gif").getImage().getScaledInstance(538, 335, Image.SCALE_DEFAULT));
    ImageIcon enemy2buff = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 2\\buff.gif").getImage().getScaledInstance(187, 195, Image.SCALE_DEFAULT));
    ImageIcon enemy2Icon = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 2\\icon.png").getImage().getScaledInstance(56, 38, Image.SCALE_DEFAULT));
    // ENEMY 3
    ImageIcon enemy3idle = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 3\\idle.gif").getImage().getScaledInstance(424, 412, Image.SCALE_DEFAULT));
    ImageIcon enemy3atk = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 3\\atk.gif").getImage().getScaledInstance(857, 544, Image.SCALE_DEFAULT));
    ImageIcon enemy3buff = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 3\\buff.gif").getImage().getScaledInstance(379, 301, Image.SCALE_DEFAULT));
    ImageIcon enemy3Icon = new ImageIcon(loadIcon("Characters\\Enemies\\Stage 3\\icon.png").getImage().getScaledInstance(56, 38, Image.SCALE_DEFAULT));
    JLabel enemyHolder = new JLabel();
    JPanel battlePanel = new JPanel();
    JLabel playerIcon = new JLabel();
    JLabel enemyIcon = new JLabel();
    JLabel enemyNAMEWriter = new JLabel("ENEMY NAME");
    JLabel enemyHPWriter = new JLabel("HP");
    JLabel enemyATKWriter = new JLabel("ATK");
    JLabel enemyDEFWriter = new JLabel("DEF");
    JLabel logWriter = new JLabel("BATTLE LOG");
    JLabel logWriter2 = new JLabel("BATTLE LOG 2");
    JLabel playerNAMEWriter = new JLabel("NAME: ");
    JLabel playerHPWriter = new JLabel("HP: ");
    JLabel playerATKWriter = new JLabel("ATK: ");
    JLabel playerDEFWriter = new JLabel("DEF: ");
    JLabel playerSPWriter = new JLabel("SP: " + skillPoints);

    void showBattleScreen() { battleScreen.build(this); }

    private boolean isPlayableClass() {
        return classWrite2.getText().contains("Swordsman")
                || classWrite2.getText().contains("Ranger")
                || classWrite2.getText().contains("Magus");
    }

    private void resetSelectedClassStats() {
        if (classWrite2.getText().contains("Ranger")) characterStats.configureRanger();
        else if (classWrite2.getText().contains("Magus")) characterStats.configureMagus();
        else characterStats.configureSwordsman();
    }

    private ImageIcon getPlayerIdle() {
        if (classWrite2.getText().contains("Ranger")) return rangerIdle;
        if (classWrite2.getText().contains("Magus")) return magusidle;
        return swordsmanIdle;
    }

    private ImageIcon getPlayerPortrait() {
        if (classWrite2.getText().contains("Ranger")) return rangerIcon;
        if (classWrite2.getText().contains("Magus")) return magusIcon;
        return swordsmanIcon;
    }

    private void setupPlayerAppearance() {
        charHolder.setIcon(getPlayerIdle());
        charHolder.setBounds(150, 150, 227, 212);
    }

    void showStageOne() { battleScreen.showStage(this, 1); }
    void showStageTwo() { battleScreen.showStage(this, 2); }
    void showStageThree() { battleScreen.showStage(this, 3); }

    void mainBGM() {
        playBGM(BGMPath);
    }

    void playBGM(File track) {
        stopBGM();
        try {
            try (AudioInputStream stream = AudioSystem.getAudioInputStream(track)) {
                musicClip = AudioSystem.getClip();
                musicClip.open(stream);
                musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void stopBGM() {
        if (musicClip != null) {
            musicClip.stop();
            musicClip.close();
            musicClip = null;
        }
    }
    void click() {
        try {
            AIS = AudioSystem.getAudioInputStream(selectPath);
            clip = AudioSystem.getClip();
            clip.open(AIS);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    void hover() {
        try {
            AIS = AudioSystem.getAudioInputStream(hoverPath);
            clip = AudioSystem.getClip();
            clip.open(AIS);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    void buttonActionListener() {
        // NEW GAME LISTENER
        playBTN.addActionListener(e -> {
            getContentPane().removeAll();
            repaint();
            click();
            showCharacterSelection();
        });
        playBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playBTN.setIcon(playPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playBTN.setIcon(play);
            }
        });
        // SAVE
        saveBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                click();
                try (PrintWriter writer = new PrintWriter(new FileWriter("saveFile.txt"))) {
                    // Save data to the file
                    writer.println("USERNAME:" + usernameWrite.getText());
                    writer.println("CLASS:" + characterStats.getClassName());
                    writer.println("HP:" + playerStats.getHp());
                    writer.println("MAXHP:" + playerStats.getMaxHp());
                    writer.println("ATK:" + playerStats.getAttack());
                    writer.println("DEF:" + playerStats.getDefense());
                    writer.println("LEVEL:" + playerStats.getLevel());
                    writer.println("XP:" + playerStats.getExperience());
                } catch (IOException a) {
                    a.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Saved File.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hover();
            }
        });
        // CONTINUE GAME LISTENER
        continueBTN.addActionListener(e -> {
            click();
            playerStats.resetProgress();
            playerStats.setHp(0);
            playerStats.setMaxHp(0);
            playerStats.setAttack(0);
            playerStats.setDefense(0);
            try (BufferedReader reader = new BufferedReader(new FileReader("saveFile.txt"))) {
                String line;
                boolean hasMaxHP = false;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        switch (key) {
                            case "USERNAME": usernameWrite.setText(value); break;
                            case "CLASS":
                                classWrite2.setText(value);
                                if (value.contains("Ranger")) characterStats.configureRanger();
                                else if (value.contains("Magus")) characterStats.configureMagus();
                                else characterStats.configureSwordsman();
                                break;
                            case "HP": playerStats.setHp(Float.parseFloat(value)); break;
                            case "MAXHP": playerStats.setMaxHp(Float.parseFloat(value)); hasMaxHP = true; break;
                            case "ATK": playerStats.setAttack(Float.parseFloat(value)); break;
                            case "DEF": playerStats.setDefense(Float.parseFloat(value)); break;
                            case "LEVEL": playerStats.setLevel(Integer.parseInt(value)); break;
                            case "XP": playerStats.setExperience(Integer.parseInt(value)); break;
                        }
                    }
                }
                if (!hasMaxHP) playerStats.setMaxHp(Math.max(characterStats.getHp(), playerStats.getHp()));
                playerStats.setExperience(Math.min(playerStats.getExperience(), experienceForNextLevel() - 1));
                getContentPane().removeAll();
                repaint();
                showCharacterMenu();
            } catch (IOException error) {
                JOptionPane.showMessageDialog(null, "Save file not found", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        continueBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                continueBTN.setIcon(continuePressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                continueBTN.setIcon(continue1);
            }
        });
        // QUIT GAME LISTENER
        quitBTN.addActionListener(e -> {
            click();
            System.exit(0);
        });
        quitBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitBTN.setIcon(quitPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitBTN.setIcon(quit);
            }
        });
        // BACK BUTTON LISTENER
        backBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //BACK
                getContentPane().removeAll();
                repaint();
                showMainMenu();
                click();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backBTN.setIcon(backPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBTN.setIcon(back);
            }
        });
        backBTN2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //BACK
                getContentPane().removeAll();
                repaint();
                showCharacterMenu();
                click();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backBTN2.setIcon(backPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBTN2.setIcon(back);
            }
        });
        // CONFIRM BUTTON LISTENER
        confirmBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //CONFIRM
                if (!name.getText().isEmpty()) {
                    click();
                    usernameWrite.setText(name.getText());
                    getContentPane().removeAll();
                    repaint();
                    playerStats.resetProgress();
                    playerStats.setMaxHp(characterStats.getHp());
                    playerStats.setHp(characterStats.getHp());
                    playerStats.setAttack(characterStats.getAttack());
                    playerStats.setDefense(characterStats.getDefense());
                    classWrite2.setText(characterStats.getClassName());
                    showCharacterMenu();
                    name.setText(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a name", "NAME ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                confirmBTN.setIcon(confirmPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                confirmBTN.setIcon(confirm);
            }
        });
        // PORTRAIT OR CLASS SELECTION LISTENER
        portrait.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //SWORDSMAN
                ImageIcon displayResize = new ScaledAnimatedIcon(swordsmanIdleIcon, 180, 160);
                classDisplay.setIcon(displayResize);
                classDisplay.setBounds(535, 135, 200, 200);
                panelDisplay.setIcon(red);
                click();
                characterStats.configureSwordsman();
                classWrite.setText(characterStats.getClassName());
                hpWrite.setText("" + (int) characterStats.getHp());
                atkWrite.setText("" + (int) characterStats.getAttack());
                defWrite.setText("" + (int) characterStats.getDefense());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                portrait.setIcon(melee2);
                portrait2.setIcon(range);
                portrait3.setIcon(magic);
                hover();
            }
        });

        portrait2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //RANGER
                ImageIcon displayResize = new ScaledAnimatedIcon(rangerIdleIcon, 180, 160);
                classDisplay.setIcon(displayResize);
                classDisplay.setBounds(535, 135, 200, 200);
                panelDisplay.setIcon(green);
                click();
                characterStats.configureRanger();
                classWrite.setText(characterStats.getClassName());
                hpWrite.setText("" + (int) characterStats.getHp());
                atkWrite.setText("" + (int) characterStats.getAttack());
                defWrite.setText("" + (int) characterStats.getDefense());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                portrait.setIcon(melee);
                portrait2.setIcon(range2);
                portrait3.setIcon(magic);
                hover();
            }
        });

        portrait3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //MAGUS
                ImageIcon displayResize = new ScaledAnimatedIcon(magusIdleIcon, 180, 160);
                classDisplay.setIcon(displayResize);
                classDisplay.setBounds(535, 135, 200, 200);
                panelDisplay.setIcon(blue);
                click();
                characterStats.configureMagus();
                classWrite.setText(characterStats.getClassName());
                hpWrite.setText("" + (int) characterStats.getHp());
                atkWrite.setText("" + (int) characterStats.getAttack());
                defWrite.setText("" + (int) characterStats.getDefense());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                portrait.setIcon(melee);
                portrait2.setIcon(range);
                portrait3.setIcon(magic2);
                hover();
            }
        });
        // BATTLE
        battleBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getContentPane().removeAll();
                repaint();
                click();
                //battleBTN.setIcon();
                showStageSelection();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hover();
            }
        });
        // EXIT
        exitBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getContentPane().removeAll();
                repaint();
                click();
                //exitBTN.setIcon(exit);
                playBTN.setIcon(play);
                continueBTN.setIcon(continue1);
                quitBTN.setIcon(quit);
                showMainMenu();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                portrait.setIcon(melee);
                portrait2.setIcon(range);
                portrait3.setIcon(magic2);
                hover();
            }
        });
        // STAGE BUTTON LISTENERS
        // STAGE 1
        stage1BTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getContentPane().removeAll();
                repaint();
                click();
                action = 0;
                skillPoints = 0;
                playerSPWriter.setText("SP: "+skillPoints);
                playerBuffCD = 0;
                playerDefeated = false;
                enemyDefeated = false;
                enemyStats.loadStageOne();
                enemyStats.scaleForPlayerLevel(playerStats.getLevel());
                enemyNAMEWriter.setText("NAME: " + enemyStats.getName());
                enemyHPWriter.setText("HP: " + (int) enemyStats.getHp());
                enemyATKWriter.setText("ATK: " + (int) enemyStats.getAttack());
                enemyDEFWriter.setText("DEF: " + (int) enemyStats.getDefense());
                showBattleScreen();
                playerNAMEWriter.setText("NAME: " + usernameWrite.getText());
                playerHPWriter.setText("HP: " + (int) playerStats.getHp());
                playerATKWriter.setText("ATK: " + (int) playerStats.getAttack());
                playerDEFWriter.setText("DEF: " + (int) playerStats.getDefense());
                setupPlayerAppearance();
                // ENEMY 1 IDLE SET
                enemyHolder.setIcon(enemy1idle);
                enemyHolder.setBounds(570, 200, 100, 140);
                // PLAYER ICON
                upperPanel.add(playerIcon);
                playerIcon.setIcon(getPlayerPortrait());
                playerIcon.setBounds(50, 20, 56, 38);
                // ENEMY ICON
                upperPanel.add(enemyIcon);
                enemyIcon.setIcon(enemy1Icon);
                enemyIcon.setBounds(835, 20, 56, 38);
                showStageOne();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hover();
            }
        });
        // STAGE 2
        stage2BTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getContentPane().removeAll();
                click();
                repaint();
                action = 0;
                skillPoints = 0;
                playerSPWriter.setText("SP: "+skillPoints);
                playerBuffCD = 0;
                playerDefeated = false;
                enemyDefeated = false;
                enemyStats.loadStageTwo();
                enemyStats.scaleForPlayerLevel(playerStats.getLevel());
                enemyNAMEWriter.setText("NAME: " + enemyStats.getName());
                enemyHPWriter.setText("HP: " + (int) enemyStats.getHp());
                enemyATKWriter.setText("ATK: " + (int) enemyStats.getAttack());
                enemyDEFWriter.setText("DEF: " + (int) enemyStats.getDefense());
                if (isPlayableClass()) {
                    showBattleScreen();
                    playerNAMEWriter.setText("NAME: " + usernameWrite.getText());
                    playerHPWriter.setText("HP: " + (int) playerStats.getHp());
                    playerATKWriter.setText("ATK: " + (int) playerStats.getAttack());
                    playerDEFWriter.setText("DEF: " + (int) playerStats.getDefense());
                    setupPlayerAppearance();
                    // ENEMY 2 IDLE SET
                    enemyHolder.setIcon(enemy2idle);
                    enemyHolder.setBounds(540, 125, 202, 247);
                    // PLAYER ICON
                    upperPanel.add(playerIcon);
                    playerIcon.setIcon(getPlayerPortrait());
                    playerIcon.setBounds(50, 20, 56, 38);
                    // ENEMY ICON
                    upperPanel.add(enemyIcon);
                    enemyIcon.setIcon(enemy2Icon);
                    enemyIcon.setBounds(835, 20, 56, 38);
                    showStageTwo();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hover();
            }
        });
        // STAGE 3
        stage3BTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getContentPane().removeAll();
                repaint();
                click();
                action = 0;
                skillPoints = 0;
                playerSPWriter.setText("SP: "+skillPoints);
                playerBuffCD = 0;
                playerDefeated = false;
                enemyDefeated = false;
                enemyStats.loadStageThree();
                enemyStats.scaleForPlayerLevel(playerStats.getLevel());
                enemyNAMEWriter.setText("NAME: " + enemyStats.getName());
                enemyHPWriter.setText("HP: " + (int) enemyStats.getHp());
                enemyATKWriter.setText("ATK: " + (int) enemyStats.getAttack());
                enemyDEFWriter.setText("DEF: " + (int) enemyStats.getDefense());
                if (isPlayableClass()) {
                    showBattleScreen();
                    playerNAMEWriter.setText("NAME: " + usernameWrite.getText());
                    playerHPWriter.setText("HP: " + (int) playerStats.getHp());
                    playerATKWriter.setText("ATK: " + (int) playerStats.getAttack());
                    playerDEFWriter.setText("DEF: " + (int) playerStats.getDefense());
                    setupPlayerAppearance();
                    // ENEMY 3 IDLE SET
                    enemyHolder.setIcon(enemy3idle);
                    enemyHolder.setBounds(415, 20, 424, 412);
                    // PLAYER ICON
                    upperPanel.add(playerIcon);
                    playerIcon.setIcon(getPlayerPortrait());
                    playerIcon.setBounds(50, 20, 56, 38);
                    // ENEMY ICON
                    upperPanel.add(enemyIcon);
                    enemyIcon.setIcon(enemy3Icon);
                    enemyIcon.setBounds(835, 20, 56, 38);
                    showStageThree();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hover();
            }
        });
        //FIGHT BUTTON LISTENER
        fightBTN.addActionListener(e -> {
            click();
            skillsPanel.setVisible(true);
            battleLogPanel.setVisible(false);
        });
        fightBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                fightBTN.setIcon(fightPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fightBTN.setIcon(fight);
            }
        });
        //RUN BUTTON LISTENER
        runBTN.addActionListener(e -> {
            click();
            runBTN.setIcon(run);
            if (action >= 2) {
                refreshPermanentLevelStats();
                playerStats.setHp(Math.min(playerStats.getMaxHp(), playerStats.getHp() * 1.5f));
                playBGM(BGMPath);
                hpWrite.setText("" + (int) playerStats.getHp());
                getContentPane().removeAll();
                repaint();
                showCharacterMenu();
                updateCharacterMenuStats();
            } else {
                skillsPanel.setVisible(false);
                battleLogPanel.setVisible(true);
                logWriter.setText("You need to do 2 actions before you can run");
                logWriter2.setText("");
            }
        });
        runBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                runBTN.setIcon(runPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                runBTN.setIcon(run);
            }
        });
        //ATTACK BUTTON LISTENER
        skill1BTN.addActionListener(e -> {
            click();
            useBasicAttack();
            enemyTurn();
            addSkillPoint();
            action++;
        });
        skill1BTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                skill1BTN.setIcon(atkPressd);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                skill1BTN.setIcon(atk);
            }
        });
        //ATTACK+ BUTTON LISTENER
        skill2BTN.addActionListener(e -> {
            click();
            usePowerAttack();
            action++;
        });
        skill2BTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                skill2BTN.setIcon(atk2Pressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                skill2BTN.setIcon(atk2);
            }
        });
        //BUFF BUTTON LISTENER
        buffBTN.addActionListener(e -> {
            click();
            useBuff();
        });
        buffBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buffBTN.setIcon(buffPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buffBTN.setIcon(buff);
            }
        });
        //SPECIAL BUTTON LISTENER
        ultBTN.addActionListener(e -> {
            click();
            useUltimate();
            action++;
        });
        ultBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ultBTN.setIcon(ultPressed);
                hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ultBTN.setIcon(ult);
            }
        });
    }
    void updateCharacterMenuStats(){
        hpWrite.setText(""+playerStats.getHp());
        atkWrite.setText(""+playerStats.getAttack());
        defWrite.setText(""+playerStats.getDefense());
    }

    int experienceForNextLevel() {
        return 100 + ((playerStats.getLevel() - 1) * 75);
    }

    private int hpPerLevel() {
        return classWrite2.getText().contains("Ranger") ? 20
                : classWrite2.getText().contains("Magus") ? 18 : 25;
    }

    private int attackPerLevel() {
        return classWrite2.getText().contains("Ranger") ? 5
                : classWrite2.getText().contains("Magus") ? 6 : 4;
    }

    private int defensePerLevel() {
        return classWrite2.getText().contains("Ranger") ? 2
                : classWrite2.getText().contains("Magus") ? 2 : 3;
    }

    private void refreshPermanentLevelStats() {
        int levelsGained = playerStats.getLevel() - 1;
        float currentHP = playerStats.getHp();
        playerStats.setMaxHp(characterStats.getHp() + levelsGained * hpPerLevel());
        playerStats.setAttack(characterStats.getAttack() + levelsGained * attackPerLevel());
        playerStats.setDefense(characterStats.getDefense() + levelsGained * defensePerLevel());
        playerStats.setHp(Math.min(currentHP, playerStats.getMaxHp()));
    }

    private void awardStageExperience(int stage) {
        int[] rewards = {0, 100, 160, 240};
        int earned = rewards[Math.max(1, Math.min(stage, 3))];
        playerStats.setExperience(playerStats.getExperience() + earned);
        boolean leveledUp = false;

        while (playerStats.getExperience() >= experienceForNextLevel()) {
            playerStats.setExperience(playerStats.getExperience() - experienceForNextLevel());
            playerStats.setLevel(playerStats.getLevel() + 1);
            leveledUp = true;
        }

        // Discard temporary combat buffs before saving progression to the next stage.
        refreshPermanentLevelStats();
        // A clear also restores a small portion of health to support the next stage.
        playerStats.setHp(Math.min(playerStats.getMaxHp(), playerStats.getHp() + playerStats.getMaxHp() * 0.15f));
        updateCharacterMenuStats();
        levelWrite.setText(String.valueOf(playerStats.getLevel()));
        experienceWrite.setText(playerStats.getExperience() + " / " + experienceForNextLevel());
        experienceBar.setMaximum(experienceForNextLevel());
        experienceBar.setValue(playerStats.getExperience());
        logWriter.setText(leveledUp
                ? "LEVEL UP! You reached level " + playerStats.getLevel() + "."
                : "Stage cleared: " + earned + " EXP earned.");
        logWriter2.setText("Level " + playerStats.getLevel() + "  |  "
                + playerStats.getExperience() + " / " + experienceForNextLevel() + " EXP");
    }

    private void restorePlayerStatsAfterDefeat() {
        resetSelectedClassStats();
        refreshPermanentLevelStats();
        playerStats.setHp(playerStats.getMaxHp());
        updateCharacterMenuStats();
    }

    // PLAYER ANIMATIONS
    private void returnPlayerToIdle() {
        Timer timer = new Timer(1500, e -> {
            charHolder.setIcon(getPlayerIdle());
            charHolder.setBounds(150, 150, 227, 212);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void playerSkill1Animation() {
        if (classWrite2.getText().contains("Ranger")) {
            charHolder.setIcon(rangerAtk);
            charHolder.setBounds(30, 98, 672, 324);
        } else if (classWrite2.getText().contains("Magus")) {
            charHolder.setIcon(magusAtk);
            charHolder.setBounds(138, -15, 688, 495);
        } else {
            charHolder.setIcon(swordsmanAtk);
            charHolder.setBounds(-173, 95, 938, 357);
        }
        returnPlayerToIdle();
    }

    private void playerSkill2Animation() {
        if (classWrite2.getText().contains("Ranger")) {
            charHolder.setIcon(rangerAtk2);
            charHolder.setBounds(-250, 75, 672, 324);
        } else if (classWrite2.getText().contains("Magus")) {
            charHolder.setIcon(magusAtk2);
            charHolder.setBounds(115, 132, 615, 256);
        } else {
            charHolder.setIcon(swordsmanAtk2);
            charHolder.setBounds(-173, 95, 938, 357);
        }
        returnPlayerToIdle();
    }

    private void playerBuffAnimation() {
        if (classWrite2.getText().contains("Ranger")) {
            charHolder.setIcon(rangerBuff);
            charHolder.setBounds(194, 154, 140, 204);
        } else if (classWrite2.getText().contains("Magus")) {
            charHolder.setIcon(magusBuff);
            charHolder.setBounds(150, 150, 227, 212);
        } else {
            charHolder.setIcon(swordsmanBuff);
            charHolder.setBounds(80, -35, 408, 492);
        }
        returnPlayerToIdle();
    }

    private void playerUltAnimation() {
        if (classWrite2.getText().contains("Ranger")) {
            charHolder.setIcon(rangerUlt);
            charHolder.setBounds(90, -80, 660, 466);
        } else if (classWrite2.getText().contains("Magus")) {
            charHolder.setIcon(magusUlt);
            charHolder.setBounds(87, -70, 873, 470);
        } else {
            charHolder.setIcon(swordsmanUlt);
            charHolder.setBounds(0, -172, 960, 712);
        }
        Timer timer = new Timer(2500, e -> returnPlayerToIdle());
        timer.setRepeats(false);
        timer.start();
    }

    // ENEMY 1 ANIMATIONS
    void enemy1ReturnIdle() {
        // ENEMY RETURN IDLE
        Timer timer = new Timer(3500, e -> enemyHolder.setIcon(enemy1idle));
        Timer timer2 = new Timer(3500, e -> enemyHolder.setBounds(570, 200, 100, 140));
        timer.start();
        timer.setRepeats(false);
        timer2.start();
        timer2.setRepeats(false);
    }

    void enemy1ATKAnimation() {
        // ENEMY 1 ATK ACTION
        Timer timer = new Timer(1500, e -> enemyHolder.setIcon(enemyAttack));
        Timer timer2 = new Timer(1500, e -> enemyHolder.setBounds(350, 25, 291, 336));
        timer2.start();
        timer.start();
        timer2.setRepeats(false);
        timer.setRepeats(false);
        enemy1ReturnIdle();
        panelVisible();
    }

    void enemy1BUFFAnimation() {
        // ENEMY 1 BUFF ACTION
        Timer timer = new Timer(1500, e -> enemyHolder.setIcon(enemy1buff));
        Timer timer2 = new Timer(1500, e -> enemyHolder.setBounds(540, 180, 144, 169));
        timer2.start();
        timer2.setRepeats(false);
        timer.start();
        timer.setRepeats(false);
        enemy1ReturnIdle();
        panelVisible();
    }

    // ENEMY 2 ANIMATIONS
    void enemy2ReturnIdle() {
        Timer timer = new Timer(3500, e -> enemyHolder.setIcon(enemy2idle));
        Timer timer2 = new Timer(3500, e -> enemyHolder.setBounds(540, 125, 202, 247));
        timer.start();
        timer.setRepeats(false);
        timer2.start();
        timer2.setRepeats(false);
    }

    void enemy2ATKAnimation() {
        // ENEMY 2 ATK ACTION
        Timer timer = new Timer(1500, e -> enemyHolder.setIcon(enemy2atk));
        Timer timer2 = new Timer(1500, e -> enemyHolder.setBounds(293, 50, 538, 335));
        timer2.start();
        timer.start();
        timer.setRepeats(false);
        timer2.setRepeats(false);
        enemy2ReturnIdle();
        panelVisible();
    }

    void enemy2BUFFAnimation() {
        // ENEMY 2 BUFF ACTION
        Timer timer = new Timer(1500, e -> enemyHolder.setIcon(enemy2buff));
        Timer timer2 = new Timer(1500, e -> enemyHolder.setBounds(535, 170, 187, 195));
        timer2.start();
        timer2.setRepeats(false);
        timer.start();
        timer.setRepeats(false);
        enemy2ReturnIdle();
        panelVisible();
    }

    // ENEMY 3 ANIMATIONS
    void enemy3ReturnIdle() {
        Timer timer = new Timer(3500, e -> enemyHolder.setIcon(enemy3idle));
        Timer timer2 = new Timer(3500, e -> enemyHolder.setBounds(415, 20, 424, 412));
        timer.start();
        timer.setRepeats(false);
        timer2.start();
        timer2.setRepeats(false);
    }

    void enemy3ATKAnimation() {
        Timer timer = new Timer(1500, e -> enemyHolder.setIcon(enemy3atk));
        Timer timer2 = new Timer(1500, e -> enemyHolder.setBounds(100, -135, 857, 544));
        timer2.start();
        timer.start();
        timer2.setRepeats(false);
        timer.setRepeats(false);
        enemy3ReturnIdle();
        panelVisible();
    }

    void enemy3BUFFAnimation() {
        Timer timer = new Timer(1500, e -> enemyHolder.setIcon(enemy3buff));
        Timer timer2 = new Timer(1500, e -> enemyHolder.setBounds(415, 20, 424, 412));
        timer2.start();
        timer.start();
        timer2.setRepeats(false);
        timer.setRepeats(false);
        enemy3ReturnIdle();
        panelVisible();
    }

    void panelVisible() {
        Timer timer = new Timer(4000, e -> lowerPanel.setVisible(true));
        timer.start();
        timer.setRepeats(false);
    }

    void panelInvisible() {
        lowerPanel.setVisible(false);
        skillsPanel.setVisible(false);
        battleLogPanel.setVisible(true);
    }

    // PLAYER SKILLS DMG AND FORMULA
    void checkBattleEnd() {
        if (playerStats.getHp() <= 0 && !playerDefeated) {
            playBGM(BGMPath);
            playerStats.setHp(0);
            playerDefeated = true;
            restorePlayerStatsAfterDefeat();
            Timer timer = new Timer(5000, e -> getContentPane().removeAll());
            Timer timer2 = new Timer(5000, e -> repaint());
            Timer timer3 = new Timer(5000, e -> showCharacterMenu());
            timer.start();
            timer.setRepeats(false);
            timer2.start();
            timer2.setRepeats(false);
            timer3.start();
            timer3.setRepeats(false);
        }
        if (enemyStats.getName().contains("Mystogan")) {
            if (enemyStats.getHp() <= 0 && !enemyDefeated) {
                playBGM(BGMPath);
                enemyStats.setHp(0);
                enemyDefeated = true;
                awardStageExperience(1);
                Timer timer = new Timer(2000, e -> getContentPane().removeAll());
                Timer timer2 = new Timer(2000, e -> repaint());
                Timer timer3 = new Timer(2000, e -> showCharacterMenu());
                timer.start();
                timer.setRepeats(false);
                timer2.start();
                timer2.setRepeats(false);
                timer3.start();
                timer3.setRepeats(false);
            }
        } else if (enemyStats.getName().contains("Sylvr")) {
            if (enemyStats.getHp() <= 0 && !enemyDefeated) {
                playBGM(BGMPath);
                enemyStats.setHp(0);
                enemyDefeated = true;
                awardStageExperience(2);
                Timer timer = new Timer(2000, e -> getContentPane().removeAll());
                Timer timer2 = new Timer(2000, e -> repaint());
                Timer timer3 = new Timer(2000, e -> showCharacterMenu());
                timer.start();
                timer.setRepeats(false);
                timer2.start();
                timer2.setRepeats(false);
                timer3.start();
                timer3.setRepeats(false);
            }
        } else if (enemyStats.getName().contains("Vestroia")) {
            if (enemyStats.getHp() <= 0 && !enemyDefeated) {
                playBGM(BGMPath);
                enemyStats.setHp(0);
                enemyDefeated = true;
                awardStageExperience(3);
                Timer timer = new Timer(2000, e -> getContentPane().removeAll());
                Timer timer2 = new Timer(2000, e -> repaint());
                Timer timer3 = new Timer(2000, e -> showCharacterMenu());
                timer.start();
                timer.setRepeats(false);
                timer2.start();
                timer2.setRepeats(false);
                timer3.start();
                timer3.setRepeats(false);
            }
        }
    }
    //MSIC SYSTEM
    void addSkillPoint() {
        skillPoints++;
        playerSPWriter.setText("SP: "+skillPoints);
    }
    // PLAYER ATK
    void useBasicAttack() {
        playerDMG = playerStats.getAttack()- enemyStats.getDefense();
        if (playerDMG < 0){
            playerDMG = 0;
        }
        enemyStats.setHp(enemyStats.getHp()-playerDMG);
        checkBattleEnd();
        // ENEMY HP UPDATE
        Timer timer = new Timer(1000, e-> enemyHPWriter.setText("HP: "+(int) enemyStats.getHp()));
        timer.start();
        timer.setRepeats(false);
        playerSkill1Animation();
        // LOG UPDATE
        logWriter.setText(usernameWrite.getText()+" dealt "+(int)playerDMG+" DMG.");
        skillsPanel.setVisible(false);
        battleLogPanel.setVisible(true);
        panelInvisible();
        panelVisible();
    }
    // PLAYER ATK+
    void usePowerAttack() {
        // SP CHECKER
        if(skillPoints <= 0){
            skillsPanel.setVisible(false);
            battleLogPanel.setVisible(true);
            logWriter.setText("This skill requires 1 SP.");
            logWriter2.setText(null);
        }else if(skillPoints >= 1){
            skillPoints--;
            playerSPWriter.setText("SP: "+skillPoints);
            playerDMG2 = (float) (playerStats.getAttack() * 1.5) - enemyStats.getDefense();
            if (playerDMG2 < 0){
                playerDMG2 = 0;
            }
            enemyStats.setHp(enemyStats.getHp() - playerDMG2);
            checkBattleEnd();
            // ENEMY HP UPDATE
            Timer timer = new Timer(1000, e -> enemyHPWriter.setText("HP: " + (int) enemyStats.getHp()));
            timer.start();
            timer.setRepeats(false);
            playerSkill2Animation();
            // LOG UPDATE
            logWriter.setText(usernameWrite.getText() + " dealt " + (int) playerDMG2 + " DMG.");
            skillsPanel.setVisible(false);
            battleLogPanel.setVisible(true);
            enemyTurn();
            panelInvisible();
            panelVisible();
        }
    }
    // PLAYER BUFF
    void useBuff(){
        if(playerBuffCD > 0){
            skillsPanel.setVisible(false);
            battleLogPanel.setVisible(true);
            logWriter.setText("Skill on CD for "+playerBuffCD+" turn/s.");
            logWriter2.setText(null);
        }else if(playerBuffCD <= 0) {
            float secretHPPlus = playerStats.getMaxHp() * 0.10f;
            playerStats.setHp(Math.min(playerStats.getMaxHp(), playerStats.getHp() + secretHPPlus));
            playerATKBUFF = playerStats.getAttack() * 0.20f;
            playerDEFBUFF = playerStats.getDefense() * 0.10f;
            playerStats.setAttack(playerStats.getAttack() + playerATKBUFF);
            playerStats.setDefense(playerStats.getDefense() + playerDEFBUFF);
            // STAT DISPLAY UPDATE
            Timer timer = new Timer(500, e -> playerHPWriter.setText("HP: " + (int) playerStats.getHp()));
            timer.start();
            timer.setRepeats(false);
            Timer timer2 = new Timer(500, e -> playerATKWriter.setText("ATK: " + (int) playerStats.getAttack()));
            timer2.start();
            timer2.setRepeats(false);
            Timer timer3 = new Timer(500, e -> playerDEFWriter.setText("DEF: " + (int) playerStats.getDefense()));
            timer3.start();
            timer3.setRepeats(false);
            playerBuffCD = 4;
            playerBuffAnimation();
            // LOG UPDATE
            logWriter.setText(usernameWrite.getText() + " rose its ATK by " + (int) playerATKBUFF + " and DEF by " + (int) playerDEFBUFF + ".");
            panelInvisible();
            panelVisible();
            addSkillPoint();
            action++;
            enemyTurn();
        }
    }
    // PLAYER ULT
    void useUltimate(){
        // SP CHECKER
        if(skillPoints >= 5){
            ultDMG = (playerStats.getAttack() * 10) - enemyStats.getDefense();
            if (ultDMG < 0){
                ultDMG = 0;
            }
            enemyStats.setHp(enemyStats.getHp() - ultDMG);
            Timer timer = new Timer(4000, e->enemyHPWriter.setText("HP: " +(int) enemyStats.getHp()));
            timer.start();
            timer.setRepeats(false);
            playerUltAnimation();
            Timer timer2 = new Timer(7000, e->checkBattleEnd());
            timer2.start();
            timer2.setRepeats(false);
            skillPoints = skillPoints - ULTIMATE_SKILL_POINT_COST;
            // SP DISPLAY UPDATE
            playerSPWriter.setText("SP: "+skillPoints);
            enemyMoveDelay();
            // LOG UPDATE
            logWriter.setText(usernameWrite.getText()+" dealt "+(int)ultDMG+" DMG.");
            //enemyMoveDelay();
            panelInvisible();
            panelVisible();
        } else {
            skillsPanel.setVisible(false);
            battleLogPanel.setVisible(true);
            // LOG UPDATE
            logWriter.setText("This skill requires 5 SP.");
            logWriter2.setText(null);
        }
    }
    // ENEMY ATK
    void enemyTurn() {
        if (!enemyDefeated){
            if (enemyStats.getName().contains("Mystogan")) {
                if (enemyBuffCD > 0) {
                    // ENEMY 1 BUFF CD CHECKER
                    enemyBuffCD--;
                    enemyAttack();
                } else if (enemyBuffCD <= 0) {
                    // ENEMY 1 BUFF APPLIER
                    enemyATKBUFF = (float) (enemyStats.getAttack() * 0.15);
                    enemyDEFBUFF = (float) (enemyStats.getDefense() * 0.10);
                    enemyStats.setAttack(enemyStats.getAttack() + enemyATKBUFF);
                    enemyStats.setDefense(enemyStats.getDefense() + enemyDEFBUFF);
                    // STATS UPDATE
                    enemyATKWriter.setText("ATK: " + (int) enemyStats.getAttack());
                    enemyDEFWriter.setText("DEF: " + (int) enemyStats.getDefense());
                    enemy1BUFFAnimation();
                    enemyBuffCD = random.nextInt(6);
                    // LOG UPDATE
                    logWriter2.setText(enemyStats.getName() + " rose its ATK by " + (int) enemyATKBUFF + " and DEF by " + (int) enemyDEFBUFF + ".");
                    panelInvisible();
                    panelVisible();
                    playerBuffCD--;
                }
            }else if (enemyStats.getName().contains("Sylvr")){
                if (enemyBuffCD > 0) {
                    // ENEMY 1 BUFF CD CHECKER
                    enemyBuffCD--;
                    enemyAttack();
                } else if (enemyBuffCD <= 0) {
                    // ENEMY 1 BUFF APPLIER
                    enemyATKBUFF = (float) (enemyStats.getAttack() * 0.15);
                    enemyDEFBUFF = (float) (enemyStats.getDefense() * 0.10);
                    enemyStats.setAttack(enemyStats.getAttack() + enemyATKBUFF);
                    enemyStats.setDefense(enemyStats.getDefense() + enemyDEFBUFF);
                    // STATS UPDATE
                    enemyATKWriter.setText("ATK: " + (int) enemyStats.getAttack());
                    enemyDEFWriter.setText("DEF: " + (int) enemyStats.getDefense());
                    enemy2BUFFAnimation();
                    enemyBuffCD = random.nextInt(6);
                    // LOG UPDATE
                    logWriter2.setText(enemyStats.getName() + " rose its ATK by " + (int) enemyATKBUFF + " and DEF by " + (int) enemyDEFBUFF + ".");
                    panelInvisible();
                    panelVisible();
                    playerBuffCD--;
                }
            }else if (enemyStats.getName().contains("Vestroia")){
                if (enemyBuffCD > 0) {
                    // ENEMY 1 BUFF CD CHECKER
                    enemyBuffCD--;
                    enemyAttack();
                } else if (enemyBuffCD <= 0) {
                    // ENEMY 1 BUFF APPLIER
                    enemyATKBUFF = (float) (enemyStats.getAttack() * 0.15);
                    enemyDEFBUFF = (float) (enemyStats.getDefense() * 0.10);
                    enemyStats.setAttack(enemyStats.getAttack() + enemyATKBUFF);
                    enemyStats.setDefense(enemyStats.getDefense() + enemyDEFBUFF);
                    // STATS UPDATE
                    enemyATKWriter.setText("ATK: " + (int) enemyStats.getAttack());
                    enemyDEFWriter.setText("DEF: " + (int) enemyStats.getDefense());
                    enemy3BUFFAnimation();
                    enemyBuffCD = random.nextInt(6);
                    // LOG UPDATE
                    logWriter2.setText(enemyStats.getName() + " rose its ATK by " + (int) enemyATKBUFF + " and DEF by " + (int) enemyDEFBUFF + ".");
                    panelInvisible();
                    panelVisible();
                    playerBuffCD--;
                }
            }
        }
    }
    // ENEMY 1 ATK
    void enemyAttack() {
        if (!enemyDefeated){
            // Defense reduces enemy damage, but a successful hit always deals at least 1.
            enemyDMG = Math.max(1, enemyStats.getAttack() - playerStats.getDefense());
            playerStats.setHp(playerStats.getHp() - enemyDMG);
            checkBattleEnd();
            // PLAYER HP UPDATE
            Timer timer = new Timer(1500, e -> playerHPWriter.setText("HP: " + (int) playerStats.getHp()));
            timer.start();
            timer.setRepeats(false);
            if (enemyStats.getName().contains("Mystogan")){
                enemy1ATKAnimation();
            }else if (enemyStats.getName().contains("Sylvr")){
                enemy2ATKAnimation();
            }else if (enemyStats.getName().contains("Vestroia")){
                enemy3ATKAnimation();
            }
            // LOG UPDATE
            logWriter2.setText(enemyStats.getName() + " dealt " + (int) enemyDMG + " DMG.");
            panelInvisible();
            panelVisible();
            playerBuffCD--;
        }
    }
    void enemyMoveDelay() {
        if(enemyStats.getName().contains("Mystogan")) {
            Timer timer = new Timer(3000, e -> enemyTurn());
            timer.start();
            timer.setRepeats(false);
        }else if(enemyStats.getName().contains("Sylvr")) {
            Timer timer = new Timer(4000, e -> enemyTurn());
            timer.start();
            timer.setRepeats(false);
        } else if(enemyStats.getName().contains("Vestroia")) {
            Timer timer = new Timer(4000, e -> enemyTurn());
            timer.start();
            timer.setRepeats(false);
        }
    }
}
