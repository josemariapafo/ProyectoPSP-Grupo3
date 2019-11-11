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
public class Model extends Thread{
    
     public void run() {
            try {
                sleep(100);
            } catch (InterruptedException e) { }         
     }
    
}
