package com.ueffort.study.compress;

import com.ueffort.study.SymbolTable.Binary;
import stdlib.BinaryStdIn;
import stdlib.BinaryStdOut;

/**
 * 游程编码
 *      1. 将连续的0或者1记录长度，保存长度的2进制（4位）
 *      2. 0和1的长度交替保存
 *      3. 最长超过255的填充一位0
 * Created by GaoJie on 2016/8/10.
 */
public class Genome {
    public static void compress(){
        char cnt = 0;
        boolean b, old = false;
        while(!BinaryStdIn.isEmpty()){
            b = BinaryStdIn.readBoolean();
            if(b != old){
                BinaryStdOut.write(cnt);
                cnt = 0;
                old = !old;
            }else{
                if(cnt == 255){
                    BinaryStdOut.write(cnt);
                    cnt = 0;
                    BinaryStdOut.write(cnt);
                }
            }
            cnt++;
        }
        BinaryStdOut.write(cnt);
        BinaryStdOut.close();
    }

    public static void expand(){
        boolean b = false;
        while(!BinaryStdIn.isEmpty()){
            char cnt = BinaryStdIn.readChar();
            for (int i = 0; i < cnt; i++)
                BinaryStdOut.write(b);
            b = !b;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args){
        if(args[0].equals("-")) compress();
        if(args[0].equals("+")) expand();
    }
}
