#pragma once
#include <string>
#include <fstream>
#include <iostream>
class WeddingDress
{

private:
	int size;
	std::string colour;
	float price;
	int quantity;
	std::string photograph;

public:
	WeddingDress(int size,const std::string& colour,float price,int quantity,const std::string& photograph);
	WeddingDress();
	void set_size(int siz);
	void set_price(float price);
	void set_quantity(int quantity);
	void set_photo(const std::string& photograph);
	void set_colour(const std::string& colour);
	int get_size();
	std::string get_colour();
	float get_price();
	int get_quantity();
	std::string get_photograph();
	WeddingDress& operator=( WeddingDress dress);
	bool operator==(WeddingDress& dress);

    friend std::ifstream & operator>>(std::ifstream& fout, WeddingDress& dress);
	friend std::ofstream & operator<<(std::ofstream& fin, WeddingDress& dress);

	~WeddingDress();
};

