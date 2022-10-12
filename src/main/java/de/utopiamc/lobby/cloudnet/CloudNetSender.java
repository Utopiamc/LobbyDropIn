package de.utopiamc.lobby.cloudnet;

import de.dytanic.cloudnet.common.concurrent.ITask;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTask;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import de.dytanic.cloudnet.ext.bridge.player.executor.PlayerExecutor;
import de.dytanic.cloudnet.ext.bridge.player.executor.ServerSelectorType;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class CloudNetSender {
    public static void sendUserNextRound(Player p, String server, ServerSelectorType serverSelectorType){
        try {
            IPlayerManager iPlayerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

            PlayerExecutor playerExecutor = iPlayerManager.getPlayerExecutor(p.getUniqueId());


            p.sendMessage("§c§lUtopia §7» Du wurdest erfolgreich auf §b" + server + " §7mit den §b" + serverSelectorType.name().toLowerCase() +  " §7verbunden");

            if (serverSelectorType.equals(ServerSelectorType.HIGHEST_PLAYERS)){
                playerExecutor.connectToTask(server, ServerSelectorType.LOWEST_PLAYERS);
            }if (serverSelectorType.equals(ServerSelectorType.LOWEST_PLAYERS)){
                playerExecutor.connectToTask(server, ServerSelectorType.HIGHEST_PLAYERS);
            }else {
                playerExecutor.connectToTask(server, ServerSelectorType.RANDOM);
            }

        }catch (Exception e1){
            Bukkit.getConsoleSender().sendMessage("Fehler bei der verschiebung nach " + server);
        }

    }

    public static int getOnlinePlayerCount(){
        int onlinePlayers;
        IPlayerManager iPlayerManager  = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);
        onlinePlayers = iPlayerManager.getOnlineCount();

        return onlinePlayers;
    }

//
//
//    private static final CloudNetDriver driver = CloudNetDriver.getInstance();
//
//    private HashMap<UUID, ServiceInfoSnapshot> privateList = new HashMap<>();
//    private static HashMap<UUID, String> quwname = new HashMap<>();
//    private static ArrayList<UUID> que = new ArrayList<>();
//    public static ArrayList<UUID> hasstart = new ArrayList<>();
//
//    public void createPrivateServer(final Player player, final String group) {
//        if (!hasstart.contains(player.getUniqueId())) {
//            //hasstart.add(player.getUniqueId());
//
//            sendPMessage(player, Main.prefix + "§7Dein Privater Server wird erstellt§8.", "§b" + group + " §cServer wird Erstellt.");
//            try {
//                @NotNull ITask<ServiceTask> iTask = driver.getServiceTaskProvider().getServiceTaskAsync(group);
//                iTask.onComplete((serviceTaskITask, serviceTask) -> {
//                    ServiceInfoSnapshot serviceInfoSnapshot = driver.getCloudServiceFactory().createCloudService(serviceTask);
//                    Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
//                        sendPMessage(player, Main.prefix + "§7Dein Privater Server erfolgreich erstellt§8.", "§b" + group + " §c§aServer erfolgreich Erstellt..", "§7Dauer ungefähr §b15Sek", true);
//
//                        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
//                            que.add(player.getUniqueId());
//                        }, 25);
//                    }, 10);
//                    privateList.put(player.getUniqueId(), serviceInfoSnapshot);
//
//                    try {
//                        Random r = new Random();
//                        Main.getMain().getMySqlAPI().sql(MySqlAPI.Database.BUNGEE, "INSERT INTO `privartgames`(`uuid`, `serverName`, `status`, `password`) VALUES ('" + player.getUniqueId().toString() + "','" + serviceInfoSnapshot.getName() + "', 'Startet...', '" + (r.nextInt(8) + 1) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + "')");
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                    quwname.put(player.getUniqueId(), group);
//                    driver.getCloudServiceProvider(serviceInfoSnapshot).start();
//                    Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
//                        sendPMessage(player, Main.prefix + "§7Dein Privater Server wird gestartet§8.", "§2Dein §9" + group + " §2Server startet...", "§7Dauer ungefähr §b15Sek", false);
//                        sendPMessage(player, Main.prefix + "§7Du wirst auf deinen Privarten server Verbunden sobalt er gestartet ist!");
//
//                    }, 25);
//
//
//                });
//            }catch (Exception e1){
//                Random r = new Random();
//
//                String fehler = "[Fehler" + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + "]";
//
//                player.sendMessage(Main.prefix + "Ein Fehler ist aufgetreten bitte melde diesen bei dem Server Team: " + fehler);
//                Bukkit.getConsoleSender().sendMessage("§cFehler beim starten von " + group + " von " + player.getName() + " " + fehler);
//                Bukkit.getConsoleSender().sendMessage("§cFehler: " + e1.getMessage());
//            }
//
//        }
//    }
//    public void createPrivateServer(final Player player, final String group, String Template) {
//        if (!hasstart.contains(player.getUniqueId())) {
//            //hasstart.add(player.getUniqueId());
//
//            sendPMessage(player, Main.prefix + "§7Dein Privater Server wird erstellt§8.", "§b" + group + " §cServer wird Erstellt.");
//            try {
//                @NotNull ITask<ServiceTask> iTask = driver.getServiceTaskProvider().getServiceTaskAsync(group);
//                iTask.onComplete((serviceTaskITask, serviceTask) -> {
//                    ServiceInfoSnapshot serviceInfoSnapshot = driver.getCloudServiceFactory().createCloudService(serviceTask);
//                    Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
//                        sendPMessage(player, Main.prefix + "§7Dein Privater Server erfolgreich erstellt§8.", "§b" + group + " §c§aServer erfolgreich Erstellt..", "§7Dauer ungefähr §b15Sek", true);
//
//                        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
//                            que.add(player.getUniqueId());
//                        }, 25);
//                    }, 10);
//                    privateList.put(player.getUniqueId(), serviceInfoSnapshot);
//
//                    try {
//                        Random r = new Random();
//                        Main.getMain().getMySqlAPI().sql(MySqlAPI.Database.BUNGEE, "INSERT INTO `privartgames`(`uuid`, `serverName`, `status`, `password`) VALUES ('" + player.getUniqueId().toString() + "','" + serviceInfoSnapshot.getName() + "', 'Startet...', '" + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + "')");
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                    quwname.put(player.getUniqueId(), group);
//                    driver.getCloudServiceProvider(serviceInfoSnapshot).start();
//                    Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
//                        sendPMessage(player, Main.prefix + "§7Dein Privater Server wird gestartet§8.", "§2Dein §9" + group + " §2Server startet...", "§7Dauer ungefähr §b15Sek", false);
//                        sendPMessage(player, Main.prefix + "§7Du wirst auf deinen Privarten server Verbunden sobalt er gestartet ist!");
//
//                    }, 25);
//
//
//                });
//            }catch (Exception e1){
//                Random r = new Random();
//
//                String fehler = "[Fehler" + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + "]";
//
//                player.sendMessage(Main.prefix + "Ein Fehler ist aufgetreten bitte melde diesen bei dem Server Team: " + fehler);
//                Bukkit.getConsoleSender().sendMessage("§cFehler beim starten von " + group + " von " + player.getName() + " " + fehler);
//                Bukkit.getConsoleSender().sendMessage("§cFehler: " + e1.getMessage());
//            }
//
//        }
//    }
//
//    public void ConnectToServer(Player p, String servername){
//        IPlayerManager iPlayerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);
//
//        PlayerExecutor playerExecutor = iPlayerManager.getPlayerExecutor(p.getUniqueId());
//
//        playerExecutor.connect(servername);
//    }
//
//    public void createServer(Player player, String group){
//        createPrivateServer(player, group);
//    }
//
//
//    public static void sendPMessage(Player p, String message){
//            p.sendMessage(message);
//    }
//    public static void sendPMessage(Player p, String message, String titel){
//
//            p.sendMessage(message);
//            p.sendTitle("", titel, 0, 50, 0);
//
//    }
//    public static void sendPMessage(Player p, String message, String titel, Boolean click){
//
//            p.sendMessage(message);
//            p.sendTitle("", titel, 0, 50, 0);
//            if (click){
//                p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL,1,1);
//            }
//
//    }
//    public static void sendPMessage(Player p, String message, String titel, String titel2, Boolean click){
//
//            if (message != null) {
//                p.sendMessage(message);
//            }
//            p.sendTitle(titel, titel2, 0, 100, 0);
//            if (click){
//                p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL,1,1);
//            }
//
//    }
//
//    public static HashMap<UUID, Integer> state = new HashMap<>();
//    public static void startServerTitel(Main main) {
//        CloudNetSender cloudNetSender = new CloudNetSender();
//        cloudNetSender.starttest(Main.getMain());
//        int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0;i < que.size(); i++){
//                    Player p = Bukkit.getPlayer(que.get(i));
//                    if (Bukkit.getOnlinePlayers().contains(p)) {
//                        if (state.containsKey(p.getUniqueId())) {
//                            state.put(p.getUniqueId(), state.get(p.getUniqueId()) + 1);
//
//                            if (state.get(p.getUniqueId()) == 4) {
//                                sendPMessage(p, null, "§2Dein §9" + quwname.get(p.getUniqueId()) + " §2Server startet...", "§7Bringe den Müll raus...", false);
//                            } else if (state.get(p.getUniqueId()) == 7) {
//                                sendPMessage(p, null, "§2Dein §9" + quwname.get(p.getUniqueId()) + " §2Server startet...", "§cDein Privarter Server ist demnächst bereit!", false);
//                            } else if (state.get(p.getUniqueId()) == 10) {
//                                sendPMessage(p, null, "§2Dein §9" + quwname.get(p.getUniqueId()) + " §2Server startet...", "§cDu wirst verbunden, sobald dein Server gestartet ist!", false);
//                            }
//                        } else {
//                            state.put(p.getUniqueId(), 0);
//                            sendPMessage(p, Main.prefix + "§7Dein Privater Server wird gestartet§8.", "§2Dein §9" + quwname.get(p.getUniqueId()) + " §2Server startet...", "§7", false);
//                        }
//                    }
//
//                }
//            }
//        }, 0, 20);
//    }
//
//    public void starttest(Main main) {
//        int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0;i < que.size(); i++){
//                    Player p = Bukkit.getPlayer(que.get(i));
//
//                    try {
//                        ResultSet resultSet = Main.getMain().getMySqlAPI().sql(MySqlAPI.Database.BUNGEE, "SELECT `status`, `serverName` FROM `privartgames` WHERE `uuid` = '" + p.getUniqueId() + "'");
//                        resultSet.next();
//                        if (resultSet.getString("status").equals("Wartelobby")) {
//                            p.sendMessage(Main.prefix + "Dein Server ist nun einsatz bereit!");
//                            p.sendMessage(Main.prefix + "Du wirst verbunden...");
//                            ConnectToServer(p, resultSet.getString("serverName"));
//                            que.remove(que.get(i));
//                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                }
//            }
//        }, 0, 30);
//    }


}
