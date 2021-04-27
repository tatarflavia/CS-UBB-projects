#include<iostream>
#include "WeddingDress.h"
#include "Repository.h"
#include "Controller.h"
#include "UI.h"
#include "RepoForBasket.h"
#include "Tests.h"
#include <fstream>
#include "Validator.h"



int main() 
{
	//Repository repo{};
	//RepoForBasket repoBasket{};
	WeddingDress dre{38,"white",6000,30,"https://media.herworld.com/public/2019/01/story/paraguay_b.jpg"};
	WeddingDress dre2{ 42,"white",4000,50,"https://ameliasposa.it/images/catalog/item/434/zoom.jpg" };
	WeddingDress dre3{ 38,"white",7000,20,"https://www.kleinfeldbridal.com/wp-content/uploads/2018/01/maggie-sottero-simple-a-line-wedding-dress-33726795.jpg" };
	WeddingDress dre4{ 42,"pink",4000,30,"https://i.pinimg.com/736x/4d/68/99/4d6899a5a116a410b7018a07440b7f0d.jpg" };
	WeddingDress dre5{ 38,"white",4500,25,"https://i.pinimg.com/736x/0f/21/e1/0f21e17876e8baf5e264f1654d24e3fd.jpg" };
	WeddingDress dre6{ 40,"cream",4000,20,"https://i.pinimg.com/originals/69/2c/44/692c44309dc5bdbefb02a24b6fe46a89.jpg" };
	WeddingDress dre7{ 42,"ivory",5000,15,"https://academyforcreativeexcellence.com/wp-content/uploads/2017/03/ivory-wedding-dress-great-ideas-for-fashion-dresses-2017-throughout-cream-wedding-dresses-simple-cream-wedding-dresses.jpg" };
	WeddingDress dre8{ 40,"cream",4000,14,"https://deysemelo.com/wp-content/uploads/2018/09/vintage-wedding-gown-preservation-with-great-wedding-dress-preservation-kit-of-wedding-gown-preservation.jpg" };
	WeddingDress dre9{ 38,"white",8000,10,"https://image.made-in-china.com/2f0j00OAeTSNIniDoZ/Sweetheart-Luxury-Bridal-Ball-Gowns-Crystals-Wedding-Dresses-Z1029.jpg" };
	WeddingDress dre10{ 40,"pink",10000,5,"https://i.pinimg.com/originals/58/0d/80/580d80acd4fc276ce4eeba2d5d480f41.jpg" };
	/*repo.add(dre);
	repo.add(dre2);
	repo.add(dre3);
	repo.add(dre4);
	repo.add(dre5);
	repo.add(dre6);
	repo.add(dre7);
	repo.add(dre8);
	repo.add(dre9);
	repo.add(dre10);*/

	
	/*{
		DynamicVector<WeddingDress> vect;
		vect = vect + dre;
		vect = dre + vect;
	}
*/
	/*std::ifstream fin("file.txt");
	if (!fin.is_open())
		return 0;
	WeddingDress dress{};
	while (fin >> dress)
		repo.add(dress);
	fin.close();*/
	Repository* repo = new FileRepository{ "file.txt" };
	//FileRepository repo{ "file.txt" };
	WeddingDressValidator validate{};

	std::string choice;
	std::cout << "Please choose a mode (<csv> or <html>) :";
	std::cin >> choice;
	if (choice.find("csv") == 0)
	{
		RepoForBasketCSV repoBasket{ "fileCSV.csv" };
		Controller ctlr{ repo,repoBasket,validate };
		UI ui{ ctlr };
		ui.first_run();
	}
	else if (choice.find("html") == 0)
	{
		RepoForBasketHTML repoBasket{ "fileHTML.html" };
		Controller ctlr{ repo,repoBasket,validate };
		UI ui{ ctlr };
		ui.first_run();
	}
	else
		std::cout << "Invalid choice!\n";

	
	/*{
		std::ifstream fin("file.txt");
		int size = 0;
		std::string colour;
		float price = 0;
		int quantity = 0;
		std::string photograph;
		while(fin >> size>>colour>>price>>quantity>>photograph)
			ctlr.add(size, colour, price, quantity, photograph);
	}*/
	
	
	//ctlr.sort_by_size();
	//ctlr.sort_by_price();

	//test();
	
	return 0;
}