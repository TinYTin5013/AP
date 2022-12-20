package com.mygdx.game;


abstract public class Creator implements Cloneable{
    private static Creator tank_creator=null;
    private static boolean Thread_One;
    abstract public Characters createCharacters(String s, int player);
}
class Tank_Creator extends  Creator{
    private static Tank_Creator tank_creator=null;
    private static boolean Thread_One;
    public Tank_Creator getInstance(){
        if(tank_creator==null){
            if(Thread_One==true){
                Thread_One=false;
                try{
                    Thread.currentThread();
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (Tank_Creator.class){
                tank_creator=new Tank_Creator();
            }
        }
        return tank_creator;
    }

    @Override
    public Characters createCharacters(String s, int player) {
        if(player==1){
            if(s.equals("Basics/Abrams_Tank.png")){return new Tank("Basics/Abrams_Tank.png", 0,0,0,0);}
            else if(s.equals("Basics/Atomic_Tank.png")){return new Tank("Basics/Atomic_Tank.png", 0,0,0,0);}
            else if(s.equals("Basics/Mark_Tank.png")){return new Tank("Basics/Mark_Tank.png", 0,0,0,0);}
            else if(s.equals("Basics/Frost_Tank.png")){return new Tank("Basics/Frost_Tank.png", 0,0,0,0);}
            else if(s.equals("Basics/Helios_Tank.png")){return new Tank("Basics/Helios_Tank.png", 0,0,0,0);}
        }else if(player==2){
            if(s.equals("Basics/Abrams_Tank.png")){return new Tank("Basics/Abrams_Tank_Reversed.png", 0,0,0,0);}
            else if(s.equals("Basics/Atomic_Tank.png")){return new Tank("Basics/Atomic_Tank_Reversed.png", 0,0,0,0);}
            else if(s.equals("Basics/Mark_Tank.png")){return new Tank("Basics/Mark_Tank_Reversed.png", 0,0,0,0);}
            else if(s.equals("Basics/Frost_Tank.png")){return new Tank("Basics/Frost_Reversed.png", 0,0,0,0);}
            else if(s.equals("Basics/Helios_Tank.png")){return new Tank("Basics/Helios_Reversed.png", 0,0,0,0);}
        }
        return null;
    }
}
