#pragma once
#include "WeddingDress.h"
#include "Validator.h"
#include <vector>
#include <iostream>
class Repository
{
protected:
	std::vector<WeddingDress> repo;

public:
	Repository();
	int get_pos(const std::string& photo);
	void readFromFile();
	std::vector<WeddingDress> get_all();
	WeddingDress get_elem_from_pos(int pos);
	bool search_elem_with_link(const std::string& photo);
	int get_size();
	int get_number_of_dresses_after_size(int size);
	virtual void add(WeddingDress& dress);
	virtual void remove(const std::string& photo);
	virtual void update(const std::string& photo, int size, float price, int quantity);

	~Repository();
};

class FileRepository :public Repository
{
private:
	std::string file;

public:
	FileRepository(std::string File);
	void readFromFile();
	void writeToFile();
	//int get_pos(const std::string& photo);
	//std::vector<WeddingDress> get_all();
	//WeddingDress* get_elem_from_pos(int pos);
	//WeddingDress* get_elem_from_link(const std::string& photo);
	//bool search_elem_with_link(const std::string& photo);
	//int get_size();
	//int get_number_of_dresses_after_size(int size);
	void add(WeddingDress& dress) override;
	void remove(const std::string& photo) override;
	void update(const std::string& photo, int size, float price, int quantity) override;
};