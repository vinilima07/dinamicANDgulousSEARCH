/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dinamica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Positivo
 */
public class DinamicSearch {
    private List<Integer> a1;
    private List<Integer> a2;
    private List<Integer> t1;
    private List<Integer> t2;
    private List<Integer> f1;
    private List<Integer> f2;
    private List<Integer> l1;
    private List<Integer> l2;

    public DinamicSearch(List<Integer> a1, List<Integer> a2, List<Integer> t1, List<Integer> t2) {
        this.a1 = a1;
        this.a2 = a2;
        this.t1 = t1;
        this.t2 = t2;
    }
    
    public List<Integer> fastestWay(){
        f1.add(a1.get(0) + a1.get(1)); //e1+a1.1
        f2.add(a2.get(0) + a2.get(1)); //e1+a1.1
        for(int j = 2; j < (a1.size()-1); j++){
            if(f1.get(j-1)+a1.get(j) <= 
               f2.get(j-1)+t2.get(j-1)+a1.get(j)){
                f1.set(j, f1.get(j-1)+a1.get(j));
                l1.set(j, 1);
            }else{
                f1.set(j, f2.get(j-1)+t2.get(j-1)+a1.get(j));
                l1.set(j, 2);
            }
            if(f2.get(j-1)+a2.get(j) <= 
               f1.get(j-1)+t1.get(j-1)+a2.get(j)){
                f2.set(j, f2.get(j-1)+a2.get(j));
                l2.set(j, 1);
            }
        }
        List<Integer> f_ = new ArrayList<>();
//        if(){
//            
//        }else {
//        }
        return f_;
    }
    
}
