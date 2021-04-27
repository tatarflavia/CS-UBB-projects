#pragma once
#include "WeddingDress.h"
#include <vector>
class RepoForBasket
{
protected:
	std::vector<WeddingDress> repo;
	float shoppingSum;
public:
	RepoForBasket();
	float get_total();
	void add_to_total(float price);

	std::vector<WeddingDress> get_all();
	WeddingDress* get_elem_from_pos(int pos);
	int get_size();
	virtual void add(WeddingDress& dress);
	virtual void execute_application();
	~RepoForBasket();
};



class RepoForBasketCSV :public RepoForBasket
{
private:
	std::string CSVfile;
public:
	RepoForBasketCSV(std::string csv);
	void writeToCSV();
	void add(WeddingDress& dress) override;
	void execute_application() override;

};


class RepoForBasketHTML :public RepoForBasket
{
private:
	std::string HTMLfile;
public:
	RepoForBasketHTML(std::string html);
	void writeToHTML();
	void add(WeddingDress& dress) override;
	void execute_application() override;

};

