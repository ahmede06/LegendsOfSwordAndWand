package factory;

import model.Hero;

public class HeroFactory {

    public static Hero createHero(String type){

        if(type.equalsIgnoreCase("warrior")){
            return new Hero("Warrior",1,50,10,10);
        }

        if(type.equalsIgnoreCase("mage")){
            return new Hero("Mage",1,35,15,5);
        }

        return new Hero("Adventurer",1,40,12,8);
    }
}