#pragma once
#include "DynamicVector.h"
#include "WeddingDress.h"
#include <vector>
class Repository
{
private:
	std::vector<WeddingDress> repo;

public:
	Repository();
	int get_pos(const std::string& photo);
	std::vector<WeddingDress> get_all();
	WeddingDress* get_elem_from_pos(int pos);
	//WeddingDress* get_elem_from_link(const std::string& photo);
	bool search_elem_with_link(const std::string& photo);
	int get_size();
	int get_number_of_dresses_after_size(int size);
	void add(WeddingDress& dress);
	void remove(const std::string& photo);
	void update(const std::string& photo , int size, float price, int quantity);

	~Repository();
};

