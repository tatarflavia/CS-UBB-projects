#include "Tests.h"
#include<iostream>
#include "WeddingDress.h"
#include "Repository.h"
#include "Controller.h"
#include "UI.h"
#include <assert.h>
#include "DynamicVector.h"

void test()
{
	Repository repo{};
	WeddingDress dre{ 38,"white",6000,30,"link1" };
	WeddingDress dre2{ 41,"white",4000,50,"link2" };
	WeddingDress dre4{ 42,"pink",4100,30,"link3" };
	WeddingDress dre5{ 38,"white",4500,25,"link4" };
	WeddingDress dre6{ 40,"cream",4000,20,"link5" };
	repo.add(dre);
	
	assert(repo.get_size() == 1);
	repo.add(dre2);
	
	assert(repo.get_size() == 2);
	repo.add(dre4);
	
	assert(repo.get_size() == 3);
	repo.add(dre5);
	
	assert(repo.get_size() == 4);
	repo.add(dre6);
	
	assert(repo.get_number_of_dresses_after_size(42) == 1);
	assert(repo.get_number_of_dresses_after_size(38) == 2);
	assert(repo.search_elem_with_link("alune") == false);
	assert(repo.search_elem_with_link("iul") == false);
	assert(repo.get_size() == 5);
	repo.remove("link1");
	assert(repo.get_size() == 4);
	repo.remove("link2");
	assert(repo.get_size() == 3);
	repo.remove("link3");
	assert(repo.get_size() == 2);
	repo.remove("link4");
	assert(repo.get_size() == 1);
	repo.remove("link5");
	assert(repo.get_size() == 0);
	

	RepoForBasket basket{};
	basket.add(dre);
	basket.add(dre2);
	basket.add(dre4);
	basket.add(dre5);
	basket.add(dre6);
	assert(basket.get_size() == 5);
	basket.add_to_total(700);
	basket.add_to_total(300);
	basket.add_to_total(1000);
	basket.add_to_total(1000);
	assert(basket.get_total() == 3000);


	Controller ctrl{ repo,basket };
	ctrl.add(38, "white", 6000, 30, "link1");
	ctrl.add(41, "white", 4000, 50, "link2");
	ctrl.add(42, "pink", 4100, 30, "link3");
	std::vector<WeddingDress> vect = ctrl.sort_by_price();
	assert(vect.size() == 3);
	assert(vect.at(0).get_price()==4000);
	assert(vect.at(1).get_price()==4100);
	assert(vect.at(2).get_price()==6000);

	std::vector<WeddingDress> vector = ctrl.sort_by_size();
	assert(vector.size() == 3);
	assert(vector.at(0).get_size()==42);
	assert(vector.at(1).get_size()==41);
	assert(vector.at(2).get_size()==38);





	assert(ctrl.size() == 3);
	assert(ctrl.get_number_of_dresses_after_size(42) == 1);
	assert(ctrl.get_number_of_dresses_after_size(38) == 1);
	ctrl.remove("link1");
	assert(ctrl.size() == 2);
	ctrl.remove("link2");
	assert(ctrl.size() == 1);
	ctrl.remove("link3");
	assert(ctrl.size() == 0);
	WeddingDress dre8{ 40,"cream",4000,14,"link8" };
	WeddingDress dre9{ 38,"white",8000,10,"link9" };
	WeddingDress dre10{ 40,"pink",10000,7,"link10" };
	
	ctrl.add(40, "cream", 4000, 14, "link8");
	ctrl.add(38, "white", 8000, 10, "link9");
	ctrl.add(40, "pink", 10000, 7, "link10");

	ctrl.addToBasket(dre8);
	ctrl.addToBasket(dre9);
	ctrl.addToBasket(dre10);
	assert(ctrl.get_price() == 25000);
	assert(ctrl.size_of_basket() == 8);
	
	
	



	



}
