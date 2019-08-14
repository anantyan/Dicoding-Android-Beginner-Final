package com.google.gallerymotogp.Component;

import java.util.ArrayList;

public class RiderDataComponent {

    public static String[][] riderData = new String[][]{
            {"Valentino Rossi", "", "", "https://upload.wikimedia.org/wikipedia/commons/8/81/Valentino_Rossi_2010_Qatar.jpg"},
            {"Marc Marquez", "", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Marc_M%C3%A1rquez_2015b.jpg/494px-Marc_M%C3%A1rquez_2015b.jpg"},
            {"Andrea Dovisioso", "", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Andrea_Dovizioso_talks_to_media.jpg/800px-Andrea_Dovizioso_talks_to_media.jpg"},
            {"Jorge Lorenzo", "", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Jorge_Lorenzo_2.jpg/1024px-Jorge_Lorenzo_2.jpg"},
            {"Casey Stoner", "", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Casey_Stoner_2010_Qatar.jpg/448px-Casey_Stoner_2010_Qatar.jpg"},
            {"Danie Pedrosa", "", "", "https://akcdn.detik.net.id/community/media/visual/2017/10/03/715a9cdf-d95c-4c63-8030-d9171431cf1e_169.jpg?w=700&q=80"},
            {"Max Biaggi", "", "", "http://photos.worldsbk.com/2018/08/03/8ec72311-4fa6-4123-8c57-01e7dee43047_full.jpg"},
            {"Marco Simocelli", "", "", "https://asset-a.grid.id/crop/0x0:0x0/700x465/photo/2019/01/22/3365094636.jpg"},
            {"Nickey Hayden", "", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Hayden_2016_%28cropped%29.jpg/417px-Hayden_2016_%28cropped%29.jpg"},
            {"Maverick Vinales", "", "", "https://www.michelinmotorsport.com/var/michelin_motorsport/storage/images/website/news/motogp-maverick-vinales-movistar-yamaha-motogp-michelin-et-yamaha-renouent-avec-la-victoire-en-australie/426822-1-fre-FR/MotoGP-Maverick-Vinales-Movistar-Yamaha-MotoGP-Michelin-et-Yamaha-renouent-avec-la-victoire-en-Australie.jpg"},
            {"Danilo Petrucci", "", "", "https://upload.wikimedia.org/wikipedia/commons/7/7d/Danilo_Petrucci_2017_Motegi.jpg"},
            {"Alex Rins", "", "", "https://cdn1-production-images-kly.akamaized.net/u37RRXtivL6kvYvJB3EEIajusls=/1280x720/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/2749879/original/009020600_1552453461-000_1EC11U.jpg"},

    };

    public static ArrayList<RiderComponent> getListData(){
        ArrayList<RiderComponent> list = new ArrayList<>();
        for(String[] a:riderData){
            RiderComponent heroComponent = new RiderComponent();

            heroComponent.setNameRider(a[0]);
            heroComponent.setGelarRider(a[1]);
            heroComponent.setDescriptionRider(a[2]);
            heroComponent.setPhotoRider(a[3]);

            list.add(heroComponent);
        }
        return list;
    }
}
