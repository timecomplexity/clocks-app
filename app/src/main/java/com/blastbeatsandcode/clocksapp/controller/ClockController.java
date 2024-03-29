package com.blastbeatsandcode.clocksapp.controller;

import android.util.Log;

import com.blastbeatsandcode.clocksapp.model.DateTimeModel;
import com.blastbeatsandcode.clocksapp.view.ClockView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * ClockController is the controller for Clocks-App.
 * Handles communicating between the views and the clock model.
 * */
public class ClockController {
    // Hold a collection of views so they may be referenced
    private ArrayList<ClockView> _views;

    private static ClockController instance = null;

    // Hold a reference to the datetime model
    private DateTimeModel _m;

    /*
     * Private constructor to create the instance if it does not already exist
     * Instead, we register the model outside of the constructor
     * For proper initialization
     * */
    private ClockController()
    {
        _views = new ArrayList<ClockView>();
    }

    public static ClockController getInstance()
    {
        if (instance == null)
        {
            instance = new ClockController();
        }
        return instance;
    }

    /*
     * Add the model to the controller
     * */
    public void registerModel(DateTimeModel model)
    {
        _m = model;
    }

    /*
     * Add view to views collection
     */
    public void registerView(ClockView v)
    {
        _views.add(v);
    }


    public Date getDate()
    {
        return _m.getDate();
    }

    /*
     * Returns "AM" or "PM" based on the current time
     */
    public String isAMOrPM()
    {
        if (getHour() >= 12)
            return "PM";
        else
            return "AM";
    }

    public int getHour()
    {
        SimpleDateFormat format = new SimpleDateFormat("HH"); // Returns the 24-hour format
        String strDate = format.format(_m.getDate());

        return Integer.parseInt(strDate);
    }

    /*
     * Returns the current minutes value
     */
    public int getMinute()
    {
        SimpleDateFormat format = new SimpleDateFormat("mm");
        String strDate = format.format(_m.getDate());
        return Integer.parseInt(strDate);
    }

    /*
     * Returns the current seconds value
     */
    public int getSecond()
    {
        SimpleDateFormat format = new SimpleDateFormat("ss");
        String strDate = format.format(_m.getDate());
        return Integer.parseInt(strDate);
    }

    /*
     * Returns the current day of month
     */
    public int getDayOfMonth ()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String strDate = format.format(_m.getDate());
        return Integer.parseInt(strDate);
    }

    /*
     * Returns the current month of year
     */
    public int getMonthOfYear()
    {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String strDate = format.format(_m.getDate());
        return Integer.parseInt(strDate);
    }

    /*
     * Returns the current year
     */
    public int getYear()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String strDate = format.format(_m.getDate());
        return Integer.parseInt(strDate);
    }

    /*
     * Update the views
     */
    public void update()
    {
        for(ClockView v : _views)
        {
            v.update();
        }
    }

    /*
     *  Sets the date in the model.
     */
    public void setTime(Date date)
    {
        _m.setDate(date);
        update();
    }

    /*
     * Carries out Undo
     */
    public void undo()
    {
        ClockCommandQueue.getInstance().undo();
    }

    /*
     * Carries out Redo
     */
    public void redo()
    {
        ClockCommandQueue.getInstance().redo();
    }

}
