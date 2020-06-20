/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.Service.EventService;
import com.mycompany.Entite.Event;

/**
 *
 * @author bhk
 */
public class AddEvent {

    Form f;
    TextField ttitre;
    TextField tdescrption;
    TextField tplace;
    Button btnajout,btnaff;

    public AddEvent() {
        f = new Form("home");
        ttitre = new TextField("","titre");
        tdescrption = new TextField("","descrption");
        tplace = new TextField("","place");
        
        
        
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
        f.add(ttitre);
        f.add(tdescrption);
       // f.add(tdescrption);
        f.add(tplace);
        
        
        
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            EventService ser = new EventService();
            //Event t = new Event(0, tnom.getText(), tetat.getText());
            
            Event t = new Event();
            t.setTitreEvent(ttitre.getText());
            t.setDescriptionEvent(tdescrption.getText());
            t.setPlaceEvent(tplace.getText());
           
            ser.ajoutEvent(t);
            

        });
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return ttitre;
    }

    public void setTnom(TextField tnom) {
        this.ttitre = tnom;
    }

}
