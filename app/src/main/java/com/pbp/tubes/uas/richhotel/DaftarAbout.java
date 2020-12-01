package com.pbp.tubes.uas.richhotel;

import java.util.ArrayList;

public class DaftarAbout {
    public ArrayList<com.pbp.tubes.uas.richhotel.Abouts> ABOUTS;

    public DaftarAbout(){
        ABOUTS = new ArrayList();
        ABOUTS.add(YOSIA);
        ABOUTS.add(DANU);
    }

    public static final com.pbp.tubes.uas.richhotel.Abouts DANU = new com.pbp.tubes.uas.richhotel.Abouts("Putu Danuhadi Wijaya", "180709770", "Fakultas Teknologi Industri",
            "Informatika", "180709770@students.uajy.ac.id", "https://vignette.wikia.nocookie.net/spongebob/images/a/ac/Spongebobwithglasses.jpeg/revision/latest?cb=20121014113150");

    public static final com.pbp.tubes.uas.richhotel.Abouts YOSIA = new com.pbp.tubes.uas.richhotel.Abouts("Yosia Galih Yudhistira", "180709971", "Fakultas Teknologi Industri",
            "Informatika", "180709971@students.uajy.ac.id", "https://static.miraheze.org/loathsomecharacterswiki/7/7e/Patrick_Star.png");

}
