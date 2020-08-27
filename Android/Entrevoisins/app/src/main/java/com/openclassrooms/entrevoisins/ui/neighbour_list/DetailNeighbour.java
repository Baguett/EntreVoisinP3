package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;

public class DetailNeighbour extends AppCompatActivity {

    String NeighbourName;
    String ProfitURL;
    String Adresse;
    String Teleph ;
    String Apropos ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_neighbour);
        getIncomingIntend();
        ImageView Caro=findViewById(R.id.Photoprofit);
        Glide.with(this).load("https://i.pravatar.cc/300").into(Caro);


        ImageButton retourProfitNeighbour = findViewById(R.id.Bouton_retour);
        retourProfitNeighbour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailNeighbour.this.finish();

            }
        });

        ArrayList<Neighbour> FavoriteNeighbour=new ArrayList<>();

        ImageButton ajouterFav=findViewById(R.id.Bouton_favoris);
        ajouterFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Neighbour mNeigh = new Neighbour(1,NeighbourName,ProfitURL,Adresse,Teleph,Apropos);
                FavoriteNeighbour.add(mNeigh);
            }
        });
    }
private void getIncomingIntend() {
 NeighbourName = getIntent().getStringExtra("neighbourg name");
ProfitURL = getIntent().getStringExtra("photo");
Adresse = getIntent().getStringExtra("adresse");
Teleph = getIntent().getStringExtra("telephone");
Apropos = getIntent().getStringExtra("aPropos");
setNeighbourProfit(NeighbourName,ProfitURL,Adresse,Teleph,Apropos);
}

private void setNeighbourProfit(String NeighbourName,String imageURL,String Adresse, String Tel,String Apropos) {
    TextView nameBig= findViewById(R.id.Neighbourgname);
    TextView nameSmall = findViewById(R.id.nameSmall);
    nameBig.setText(NeighbourName);
    nameSmall.setText(NeighbourName);

    ImageView photoProfit = findViewById(R.id.Photoprofit);
    Glide.with(this)
            .asBitmap()
            .load(imageURL)
            .into(photoProfit);

    TextView adresse = findViewById(R.id.adresse);
    adresse.setText(Adresse);

    TextView telephone = findViewById(R.id.Neighbourgtel);
    telephone.setText(Tel);

    TextView apropos = findViewById(R.id.apropos);
    apropos.setText(Apropos);

}




}
