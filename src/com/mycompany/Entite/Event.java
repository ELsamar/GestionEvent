/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

//import java.sql.Time;
//import java.sql.Time;
import java.util.Collection;
import java.util.Date;

import java.util.TimeZone;

/**
 *
 * @author samar
 */
public class Event  {
     private static int event_courant;
    private Integer idEvent;
    private User idcreateur;
    private String titreEvent;
    private Date dateStartEvent;
    private String descriptionEvent;
    private String typeEvent;
    private String EtatEvent;
    private Integer realiseEvent;
    private String placeEvent;
    private String afficheEvent;
    private Date dateAjoutEvent;
    private Integer nb;
    private TimeZone heure ;
    private Integer VuEvent;
    private Integer etoile;
    
    private String longitude; 
    private String latitude;
/*******************************************************/
    public Event() {
    }

    public Event(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public Event(Integer idEvent, User idcreateur, String titreEvent, Date dateStartEvent, String descriptionEvent, String typeEvent, String EtatEvent, Integer realiseEvent, String placeEvent, String afficheEvent, Date dateAjoutEvent, Integer nb, TimeZone heure, Integer VuEvent, Integer etoile) {
        this.idEvent = idEvent;
        this.idcreateur = idcreateur;
        this.titreEvent = titreEvent;
        this.dateStartEvent = dateStartEvent;
        this.descriptionEvent = descriptionEvent;
        this.typeEvent = typeEvent;
        this.EtatEvent = EtatEvent;
        this.realiseEvent = realiseEvent;
        this.placeEvent = placeEvent;
        this.afficheEvent = afficheEvent;
        this.dateAjoutEvent = dateAjoutEvent;
        this.nb = nb;
        this.heure = heure;
        this.VuEvent = VuEvent;
        this.etoile = etoile;
    }
/************************************************************************************************/
    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public Event(User idcreateur, String titreEvent, Date dateStartEvent, String descriptionEvent, String typeEvent, String EtatEvent, Integer realiseEvent, String placeEvent, String afficheEvent, Date dateAjoutEvent, Integer nb, TimeZone heure, Integer VuEvent, Integer etoile) {
        this.idcreateur = idcreateur;
        this.titreEvent = titreEvent;
        this.dateStartEvent = dateStartEvent;
        this.descriptionEvent = descriptionEvent;
        this.typeEvent = typeEvent;
        this.EtatEvent = EtatEvent;
        this.realiseEvent = realiseEvent;
        this.placeEvent = placeEvent;
        this.afficheEvent = afficheEvent;
        this.dateAjoutEvent = dateAjoutEvent;
        this.nb = nb;
        this.heure = heure;
        this.VuEvent = VuEvent;
        this.etoile = etoile;
    }

    public Event(Integer idEvent, User idcreateur, String titreEvent, Date dateStartEvent, String descriptionEvent, String typeEvent, String EtatEvent, Integer realiseEvent, String placeEvent, String afficheEvent, Date dateAjoutEvent, Integer nb, TimeZone heure, Integer VuEvent, Integer etoile, String longitude, String latitude) {
        this.idEvent = idEvent;
        this.idcreateur = idcreateur;
        this.titreEvent = titreEvent;
        this.dateStartEvent = dateStartEvent;
        this.descriptionEvent = descriptionEvent;
        this.typeEvent = typeEvent;
        this.EtatEvent = EtatEvent;
        this.realiseEvent = realiseEvent;
        this.placeEvent = placeEvent;
        this.afficheEvent = afficheEvent;
        this.dateAjoutEvent = dateAjoutEvent;
        this.nb = nb;
        this.heure = heure;
        this.VuEvent = VuEvent;
        this.etoile = etoile;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    
    
    
    
    public User getIdcreateur() {
        return idcreateur;
    }

    public void setIdcreateur(User idcreateur) {
        this.idcreateur = idcreateur;
    }

    public String getTitreEvent() {
        return titreEvent;
    }

    public void setTitreEvent(String titreEvent) {
        this.titreEvent = titreEvent;
    }

    public Date getDateStartEvent() {
        return dateStartEvent;
    }

    public void setDateStartEvent(Date dateStartEvent) {
        this.dateStartEvent = dateStartEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public String getEtatEvent() {
        return EtatEvent;
    }

    public void setEtatEvent(String EtatEvent) {
        this.EtatEvent = EtatEvent;
    }

    public Integer getRealiseEvent() {
        return realiseEvent;
    }

    public void setRealiseEvent(Integer realiseEvent) {
        this.realiseEvent = realiseEvent;
    }

    public String getPlaceEvent() {
        return placeEvent;
    }

    public void setPlaceEvent(String placeEvent) {
        this.placeEvent = placeEvent;
    }

    public String getAfficheEvent() {
        return afficheEvent;
    }

    public void setAfficheEvent(String afficheEvent) {
        this.afficheEvent = afficheEvent;
    }

    public Date getDateAjoutEvent() {
        return dateAjoutEvent;
    }

    public void setDateAjoutEvent(Date dateAjoutEvent) {
        this.dateAjoutEvent = dateAjoutEvent;
    }

    public Integer getNb() {
        return nb;
    }

    public void setNb(Integer nb) {
        this.nb = nb;
    }

    public TimeZone getHeure() {
        return heure;
    }

    public void setHeure(TimeZone heure) {
        this.heure = heure;
    }

    public Integer getVuEvent() {
        return VuEvent;
    }

    public void setVuEvent(Integer VuEvent) {
        this.VuEvent = VuEvent;
    }

    public Integer getEtoile() {
        return etoile;
    }

    public void setEtoile(Integer etoile) {
        this.etoile = etoile;
    }

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", idcreateur=" + idcreateur + ", titreEvent=" + titreEvent + ", dateStartEvent=" + dateStartEvent + ", descriptionEvent=" + descriptionEvent + ", typeEvent=" + typeEvent + ", EtatEvent=" + EtatEvent + ", realiseEvent=" + realiseEvent + ", placeEvent=" + placeEvent + ", afficheEvent=" + afficheEvent + ", dateAjoutEvent=" + dateAjoutEvent + ", nb=" + nb + ", heure=" + heure + ", VuEvent=" + VuEvent + ", etoile=" + etoile + ", longitude=" + longitude + ", latitude=" + latitude + '}' + "\n";
    }

  

  

    public static int getEvent_courant() {
        return event_courant;
    }

    public static void setEvent_courant(int event_courant) {
        Event.event_courant = event_courant;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

 
    
    
    
    /********************************************/
 
}
