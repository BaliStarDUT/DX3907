package top.hunaner.lol.dao.repository;

import org.springframework.stereotype.Repository;
import top.hunaner.lol.entity.Lolhero;
import top.hunaner.lol.utils.ResourceUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HeroResourceRepository {
    static List<String> heroImageList;
    static List<String> heroSoundList;
    static List<Lolhero> heroList = new ArrayList<>() ;

    public static List<Lolhero> getHeroResource(){
        if(null == heroImageList){
            heroImageList = ResourceUtil.listDirectory(new File("/Users/aliyun/Downloads/image_champions"),"Square");
        }
        if(null == heroSoundList){
            heroSoundList = ResourceUtil.listDirectory(new File("/Users/aliyun/Downloads/sound_champions"),"mp3");
        }
        if(!(heroList.size()>0)){
            for(int i=0;i<heroImageList.size();i++){
                String imageName = heroImageList.get(i);
                String soundName = heroSoundList.get(i);
                Lolhero hero = new Lolhero("费德提克",soundName.substring(0,soundName.length()-3),
                    "末日使者","稻草人","法师",imageName,soundName);
                heroList.add(hero);
            }
        }

        return heroList;
    }


}
