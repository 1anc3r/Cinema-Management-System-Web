package me.lancer.cms.idao;

import java.util.List;
import java.util.Map;

import me.lancer.cms.model.DataDict;

public interface IDataDictDAO {

	public int insert(DataDict ddict);

	public int update(DataDict ddict);

	public int delete(int id);

	public List<DataDict> select(String condt);

	public List<DataDict> findByID(int id);

	public void findAllSonByID(List<DataDict> list, int id);

	public boolean hasChildren(int id);

	public List<DataDict> select_(Map<String, String> map);
}
