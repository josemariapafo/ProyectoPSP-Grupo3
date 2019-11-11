/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Seba
 */
public class Producto {  
    
    public synchronized void get() {
        notifyAll();
    }


    public synchronized void put() {
        notifyAll();
    }
    
}
