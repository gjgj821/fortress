package com.ueffort.study;

import stdlib.StdIn;
import stdlib.StdOut;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Crontab 的逻辑实现
 * 1. 支持5项: 分钟, 小时, 天(每月几号), 月, 周(每周几号)
 * 2. 支持*:任意, /:步长, -:范围, ,:或
 * Created by tutu on 16-7-7.
 */
public class Crontab {
    private class Setting{
        int[] minute;
        int[] hour;
        int[] day;
        int[] month;
        int[] week;
        public Setting(int[] minute, int[] hour, int[] day, int[] month, int[] week){
            this.minute = minute;
            this.hour = hour;
            this.day = day;
            this.month = month;
            this.week = week;
        }

        public boolean verify(int minute, int hour, int day, int month, int week){
            if (this.minute.length > 0 && !contains(minute, this.minute)) return false;
            if (this.hour.length > 0 && !contains(hour, this.hour)) return false;
            if (this.day.length > 0 && !contains(day, this.day)) return false;
            if (this.month.length > 0 && !contains(month, this.month)) return false;
            if (this.week.length > 0 && !contains(week, this.week)) return false;
            return true;
        }

        private boolean contains(int key, int[] a){
            int i = BinarySearch.rank(key, a);
            return i >= 0;
        }
    }

    public Setting parse(String item) throws Exception{
        String[] s = item.split(" ");
        if (s.length != 5){
            throw new Exception("Crontab need: minute hour day month week");
        }
        int[] minute = analyze(s[0], 60);
        if(minute.length > 0) Arrays.sort(minute);
        int[] hour = analyze(s[1], 24);
        if(hour.length > 0) Arrays.sort(hour);
        int[] day = analyze(s[2], 31);
        if(day.length > 0) Arrays.sort(day);
        int[] month = analyze(s[3], 12);
        if(month.length > 0) Arrays.sort(month);
        int[] week = analyze(s[4], 7);
        if(week.length > 0) Arrays.sort(week);
        return new Setting(minute, hour, day, month, week);
    }

    public static int[] analyze(String s, int limit){
        int[] i = new int[0];
        if (Objects.equals(s, "*")) return i;
        if (s.contains(",")){
            // 直接合并
            String[] ss = s.split(",");
            for (String s1 : ss) {
                i = concat(i, analyze(s1, limit));
            }
        }else if(s.contains("/")){
            // 步长
            String[] ss = s.split("/");
            int step = Integer.parseInt(ss[1]);
            if (step > limit){
                i = new int[1];
                i[0] = 0;
            }else{
                int[] range = analyze(ss[0], limit);
                if (range.length == 0) {
                    range = new int[1];
                    range[0] = limit;
                }
                int[] tmp;
                int now = step + 0;
                for (int rs : range){
                    tmp = new int[rs / step];
                    for (int t = 0; now <= rs; t++, now += step){
                        tmp[t] = now;
                    }
                    i = concat(i, tmp);
                }
            }
        }else if(s.contains("-")){
            // 范围补全
            String[] ss = s.split("-");
            int[] a = new int[2];
            a[0] = Integer.parseInt(ss[0]);
            a[1] = Integer.parseInt(ss[1]);
            if (a[0] > a[1]){
                int t = a[0];
                a[0] = a[1];
                a[1] = t;
            }
            i = new int[a[1] - a[0] + 1];
            for (int t = a[0], index = 0; t <= a[1]; t++, index++){
                i[index] = t;
            }
        }else{
            i = new int[1];
            i[0] = Integer.parseInt(s);
            if (i[0] > limit) i[0] = limit;
        }
        return i;
    }

    static int[] concat(int[] a, int[] b) {
        int[] c= new int[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public boolean isActive(Setting item, Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return item.verify(
                c.get(Calendar.MINUTE),
                c.get(Calendar.HOUR),
                c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_WEEK)
        );
    }

    public static void main(String[] args){
        Crontab crontab = new Crontab();

        String item = StdIn.readLine();
        try{
            Setting setting = crontab.parse(item);
            DateFormat df = DateFormat.getDateInstance();
            while(!StdIn.isEmpty()){
                String d = StdIn.readLine();
                try{
                    Date date = df.parse(d);
                    boolean b = crontab.isActive(setting, date);

                    StdOut.println(b);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
