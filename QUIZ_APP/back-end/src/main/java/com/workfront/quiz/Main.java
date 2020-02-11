package com.workfront.quiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println((Enums.ADMIN.name()).equals("ADMIN"));
    }
}

 enum  Enums{

    ADMIN, USER
}
