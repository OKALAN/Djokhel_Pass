package com.example.diokhlpass.byt;

import java.util.ArrayList;
import java.util.Map;

public class Bus<Select_bus> {

    int idBus;
  //  String ville_dept;
    //String ville_dest;
   // String horaire;
    Map<String, ArrayList<Integer>> Select_bus;

    public Bus(int idBus, Map<String, ArrayList<Integer>> select_bus) {
        this.idBus = idBus;
        Select_bus = select_bus;
    }

}
