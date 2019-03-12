package net.argania.core.tablist.update;

import net.argania.core.GuildPlugin;
import net.argania.core.Utils.Logger;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.users.UserManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.objects.users.User;
import net.argania.core.tablist.RankList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class TabThread extends Thread {

    private static TabThread instance;

    private static Comparator<User> usersComparator = (o1, o2) -> o2.getPoints() - o1.getPoints();

    private static Comparator<Guild> guildsComparator = (o1, o2) -> o2.getPoints() - o1.getPoints();

    private static AtomicInteger ai = new AtomicInteger();
    private RankList rankList;
    private Executor executor;
    private Object locker;

    public TabThread() {
        instance = this;
        rankList = new RankList();
        locker = new Object();
        this.setName("TabThread");
        this.start();
    }

    public static void restart() {
        if (instance == null) {
            Logger.warning("TabThread instance cannot be null!");
            return;
        }
        synchronized (instance.locker) {
            instance.locker.notify();
        }
    }

    public static TabThread getInstance() {
        return instance;
    }

    public static void setInstance(TabThread instance) {
        TabThread.instance = instance;
    }

    private static Comparator<User> getUsersComparator() {
        return usersComparator;
    }

    public static void setUsersComparator(Comparator<User> usersComparator) {
        TabThread.usersComparator = usersComparator;
    }

    private static Comparator<Guild> getGuildsComparator() {
        return guildsComparator;
    }

    public static void setGuildsComparator(Comparator<Guild> guildsComparator) {
        TabThread.guildsComparator = guildsComparator;
    }

    public static AtomicInteger getAi() {
        return ai;
    }

    public static void setAi(AtomicInteger ai) {
        TabThread.ai = ai;
    }

    @Override
    public void run() {
        try {
            while (true) {
                List<User> stats = new ArrayList<>(UserManager.getUsers().values());
                stats.sort(getUsersComparator());
                List<RankList.Data<User>> toAddPlayers = new LinkedList<>();
                for (User u : stats) {
                    toAddPlayers.add(new RankList.Data<>(u, u.getPoints()));
                }
                rankList.setTopPlayers(toAddPlayers);
                List<Guild> guilds = new ArrayList<>(GuildManager.getGuilds().values());
                guilds.sort(getGuildsComparator());
                List<RankList.Data<Guild>> toAddGuilds = new LinkedList<>();
                for (Guild g : guilds) {
                    toAddGuilds.add(new RankList.Data<>(g, g.getPoints()));
                }
                rankList.setTopGuilds(toAddGuilds);
                new TabHighUpdateTask().runTaskLaterAsynchronously(GuildPlugin.getPlugin(), 1L);
                synchronized (locker) {
                    locker.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RankList getRankList() {
        return rankList;
    }

    public void setRankList(RankList rankList) {
        this.rankList = rankList;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public Object getLocker() {
        return locker;
    }

    public void setLocker(Object locker) {
        this.locker = locker;
    }

}
