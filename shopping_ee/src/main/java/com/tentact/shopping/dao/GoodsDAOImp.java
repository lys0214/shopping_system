package com.tentact.shopping.dao;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tentact.shopping.entity.Goods;
import com.tentact.shopping.entity.Kind;
import com.tentact.shopping.util.GetUUID;

public class GoodsDAOImp extends BaseDAO implements GoodsDAO {
	File file = new File("E:\\workspace\\shopping1_io\\src\\com\\tentact\\shopping\\dao\\goodsInfo.txt");
	static Goods[] arr = new Goods[10];
	static {
		Kind kind1 = new Kind(1, "食品", "");
		Goods goods1 = new Goods("g01", "桃李面包", 5, 0.2, "2021-9-13", kind1);
		Goods goods2 = new Goods("g02", "金锣火腿", 4, 0.2, "2021-9-13", kind1);
		Kind kind2 = new Kind(2, "服饰", "");
		Goods goods3 = new Goods("g03", "LV大衣", 8888, 4, "2021-9-13", kind2);
		Goods goods4 = new Goods("g04", "连衣裙", 500, 2, "2021-9-13", kind2);
		// 存到数组中
		arr[0] = goods1;
		arr[1] = goods2;
		arr[2] = goods3;
		arr[3] = goods4;
	}
	@Override
	public List<Goods> selectAll() {
		List<String> listStr = this.readTxt(file);
		// 循环收集
		List<Goods> list = new ArrayList<Goods>();
		for(String s:listStr) {
			String[] arr = s.split("#");
			Goods goods = new Goods();
			goods.setGoodsId(arr[0]);
			goods.setGoodsName(arr[1]);
			goods.setGoodsPrice(Double.parseDouble(arr[2]));
			goods.setGoodsWeight(Double.parseDouble(arr[3]));
			goods.setCreateDate(arr[4]);
			Kind kind = new Kind();
			kind.setKindId(Integer.parseInt(arr[5]));
			kind.setKindName(arr[6]);
			kind.setKindMemo(arr[7]);
			goods.setKind(kind);
			list.add(goods);
		}
		return list;
	}

	@Override
	public Goods select(String goodsId) {
		List<Goods> list = this.selectAll();
		for(Goods g:list) {
			if(g.getGoodsId().equals(goodsId)) {
				return g;
			}
		}
		return null;
	}

	@Override
	public boolean update(Goods goods) {
		// 先读取txt内容
		List<String> listStr = this.readTxt(file);
		for(int i=0;i<listStr.size();i++) {
			// 循环找到对应的数据
			String[] arr = listStr.get(i).split("#");
			if(arr[0].equals(goods.getGoodsId())) {
				// 替换数据
				String str = arr[0]+"#"+goods.getGoodsName()+"#"+goods.getGoodsPrice()+"#"+goods.getGoodsWeight()
				+"#"+goods.getCreateDate()+"#"+arr[5]+"#"+arr[6]+"#"+arr[7];
				listStr.set(i, str);
			}
		}
		// 写入txt
		return this.writeTxt(file, listStr, false);
	}

	@Override
	public boolean delete(String goodsId) {
		// 先读取txt内容
		List<String> listStr = this.readTxt(file);
		for(int i=0;i<listStr.size();i++) {
			// 循环找到对应的数据
			String[] arr = listStr.get(i).split("#");
			if(arr[0].equals(goodsId)) {
				// 移除该数据
				listStr.remove(i);
			}
		}
		// 写入txt
		return this.writeTxt(file, listStr, false);
	}

	@Override
	public boolean insert(Goods goods) {
		String str = GetUUID.getUuidCode()+"#"+goods.getGoodsName()+"#"+goods.getGoodsPrice()+"#"+goods.getGoodsWeight()
		+"#"+goods.getCreateDate()+"#"+goods.getKind().getKindId()+"#"+goods.getKind().getKindName()+"#"+goods.getKind().getKindMemo();
		List<String> listStr = new ArrayList<String>();
		listStr.add(str);
		return this.writeTxt(file, listStr, true);
	}

	@Override
	public <T> T rowMapper(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
