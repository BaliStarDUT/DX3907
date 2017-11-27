package top.hunaner.lol.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.hunaner.lol.dao.QueryDao;
import top.hunaner.lol.entity.Lolhero;

import java.util.Collection;

@Repository
public class HerosRepositoryImpl implements HerosRepository {
    @Autowired
    QueryDao dao;

    @Override
    public Collection<Lolhero> findByType(String type) {
        return dao.query(Lolhero.class,"select * from lolhero where type = ?",type);
    }

    @Override
    public Lolhero findById(int id) {
        return dao.query(Lolhero.class,"select * from lolhero where id = ?",id).get(0);
    }

    @Override
    public Collection<Lolhero> findAll() {
//        return dao.query(Lolhero.class,"select * from lolhero");
        return HeroResourceRepository.getHeroResource();
    }

    @Override
    public Lolhero save(Lolhero hero) {
        return (Lolhero) dao.save(hero);
    }

    @Override
    public Lolhero findByName(String name) {
        Lolhero hero = new Lolhero(name,name,
                "末日使者","稻草人","法师",name,name);
        return hero;
    }
}
