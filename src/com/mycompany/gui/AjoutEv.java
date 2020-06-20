/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
//import com.mycompany.Service.FileStack;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Cyrine
 */
public class AjoutEv {
    Form f;
    SpanLabel text, dateTxt, datePicked;
    TextField nom, desc, prix, stock;
    Picker p, date;
    String[] type={"Musical","Video Projection", "Educative", "Animation"};
    Button imgbtn, addbtn;
    String path;
    ImageViewer imgv;
    Container imgCtn, dateCtn;
    private Resources theme;
    
    public AjoutEv() {
        f = new Form("New Event", BoxLayout.y());
     //   f.getAllStyles().setBgColor(0xfff2e6);
//        f.getToolbar().addCommandToLeftBar("", UIManager.initFirstTheme("/theme").getImage("cal_left_arrow.png"), 
//                ev->{ new AffichageEv().getF().show();});
        p = new Picker();
        p.setStrings(type);
        p.setSelectedString(type[0]);
        p.getStyle().setFgColor(0x2A2F43);
        FontImage.setMaterialIcon(p, FontImage.MATERIAL_KEYBOARD_ARROW_DOWN);
        dateCtn = new Container(BoxLayout.y());
        Calendar cld = new Calendar();
        dateTxt = new SpanLabel("Choose a date :");
        dateTxt.getTextAllStyles().setFgColor(0x2A2F43);
        dateTxt.getTextAllStyles().setPaddingTop(2);
        dateTxt.getTextAllStyles().setPaddingBottom(2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        datePicked = new SpanLabel("> Event's date : " + String.valueOf(format.format(cld.getSelectedDay())));
        datePicked.getTextAllStyles().setFgColor(0xff6600);
        datePicked.getTextAllStyles().setPaddingTop(2);
        datePicked.getTextAllStyles().setPaddingBottom(2);
        dateCtn.add(dateTxt);
        dateCtn.add(cld);
        cld.addActionListener(a-> {
            dateCtn.add(datePicked);
            System.out.println(String.valueOf(format.format(cld.getSelectedDay())));
        });
//        date = new Picker();
//        date.getStyle().setFgColor(0x2A2F43);
//        FontImage.setMaterialIcon(date, FontImage.MATERIAL_KEYBOARD_ARROW_DOWN);
        text = new SpanLabel("Please, fill all the fields below :");
        text.getTextAllStyles().setFgColor(0x00aced);
        text.getTextAllStyles().setPaddingTop(2);
        text.getTextAllStyles().setPaddingBottom(3);
        nom = new TextField("", "Event's name..", 50, TextField.ANY);
        nom.getStyle().setFgColor(0x2A2F43);
        desc = new TextField("", "Event's description..", 500, TextField.ANY); 
        desc.getStyle().setFgColor(0x2A2F43);
        prix = new TextField("", "Price..", 10, TextField.DECIMAL);
        prix.getStyle().setFgColor(0x2A2F43);
        stock = new TextField("", "Amount of tickets..", 10, TextField.NUMERIC);
        stock.getStyle().setFgColor(0x2A2F43);
        imgbtn = new Button("Add Poster");
        addbtn = new Button("Save");
        addbtn.getStyle().setMarginTop(5);
        imgCtn = new Container();
        imgCtn.add(imgbtn);
        f.add(text);
//        f.add(nom).add(date).add(p).add(desc);
        f.add(nom).add(dateCtn).add(p).add(desc);
        f.add(imgCtn);
        f.add(prix).add(stock);
        f.add(addbtn);
        
        imgbtn.addActionListener(e->{
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if( evt!=null && evt.getSource()!=null){
                        path = (String)evt.getSource();
                        Image image = null;
                        try {
                            image = Image.createImage(FileSystemStorage.getInstance()
                                    .openInputStream(path)).fill(125, 175);
                        } catch (IOException ex) {
                            Dialog.show("Error", ex.getMessage(), "OK", null);
                        }
                        imgv = new ImageViewer(image);
                        imgv.getStyle().setMarginLeft(40);
                        imgCtn.add(imgv);
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
        
        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (nom.getText().equals(null))
                    Dialog.show("Error", "Please, enter a valid name!", "Ok", null);
                else if (datePicked.getText().equals(null))
                    Dialog.show("Error", "Please, select a date!", "Ok", null);
                else if (desc.getText().equals(null))
                    Dialog.show("Error", "Please, enter a valid description!", "Ok", null);
                else if (prix.getText().equals(null) )
                    Dialog.show("Error", "Please, enter a valid price!", "Ok", null);
                else if (stock.getText().equals(null)  )
                    Dialog.show("Error", "Please, enter a valid amount of tickets!", "Ok", null);
                else {
                    ConnectionRequest cnx = new ConnectionRequest();
                    cnx.setUrl("http://localhost/ProjetV2/web/app_dev.php/Projet/Client/AddEvDMobile?nom=" 
                            +nom.getText()+"&type="+p.getText()+"&desc="+desc.getText()+
                            "&prix="+prix.getText()+"&stock="+stock.getText()+"&affiche="+path+
                            "&date="+String.valueOf(format.format(cld.getSelectedDay()))+"");
                    cnx.addResponseListener((e) -> {
                        String str = new String(cnx.getResponseData());
                        System.out.println(str);
                    });
                    NetworkManager.getInstance().addToQueue(cnx);
                    Dialog.show("Success", "Event added successfully!", "Ok", null);
                  //  new AffichageEv().getF().show();
                }
        }
        });
        
    } 
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
