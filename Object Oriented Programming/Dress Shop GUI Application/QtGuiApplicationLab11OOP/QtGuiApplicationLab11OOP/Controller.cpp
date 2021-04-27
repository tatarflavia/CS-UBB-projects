#include "Controller.h"
#include<string>



//constructor
Controller::Controller(Repository & Repo, RepoForBasket& RepoBasket, WeddingDressValidator& valid) :repo{ Repo }, repoBasket{ RepoBasket }, validate{ valid }
{
}
//returns the size of the repo
int Controller::size()
{
	return this->repo.get_size();
}
//returns the size of the shopping basket
int Controller::size_of_basket()
{
	return this->repoBasket.get_size();
}
//returns the price from the shopping basket
float Controller::get_price()
{
	return this->repoBasket.get_total();
}

//makes an elem and adds it to the repo
void Controller::add(int size, const std::string & colour, float price, int quantity, const std::string & photograph)
{
	WeddingDress dress{ size,colour,price,quantity,photograph };
	//this->validate.validate(dress);
	this->repo.add(dress);
	UndoAction* act = new UndoAdd{ dress,this->repo };
	this->undoActions.push_back(act);
	UndoAction* act2 = new UndoRemove{ dress,this->repo };
	this->redoActions.push_back(act2);
	/*unique_ptr<UndoAction> act = make_unique<UndoAdd>(dress, this->repo);
	this->undoActions.push_back(move(act));
	unique_ptr<UndoAction> act2 = make_unique<UndoRemove>(dress, this->repo);
	this->redoActions.push_back(move(act2));*/
}
void Controller::addToBasket(std::string photo)
{
	/*int pos = this->repo->get_pos(photo);
	WeddingDress dress = *this->repo->get_elem_from_pos(pos);*/
	for (auto i : this->repo.get_all()) 
	{
		if (i.get_photograph().find(photo) == 0)
		{
			this->repoBasket.add(i);
			this->repoBasket.add_to_total(i.get_price());
			break;
		}
	}

}
//returns the number of dresses that have the size=int size from the repo
int Controller::get_number_of_dresses_after_size(int size)
{
	return this->repo.get_number_of_dresses_after_size(size);
}
//removes an elem from the repo that has the link=photo
void Controller::remove(const std::string & photograph)
{
	int pos = this->repo.get_pos(photograph);
	WeddingDress dress = this->repo.get_elem_from_pos(pos);
	this->repo.remove(photograph);
	
	UndoAction* act = new UndoRemove{dress ,this->repo };
	this->undoActions.push_back(act);
	UndoAction* act2 = new UndoAdd{ dress,this->repo };
	this->redoActions.push_back(act2);
	/*unique_ptr<UndoAction> act = make_unique<UndoAdd>(this->repo.get_all().at(pos), this->repo);
	this->undoActions.push_back(move(act));
	unique_ptr<UndoAction> act2 = make_unique<UndoRemove>(this->repo.get_all().at(pos), this->repo);
	this->redoActions.push_back(move(act2));*/

}
//updates an elem from the repo that has the link=photo
void Controller::update(const std::string & photo, int size, float price, int quantity)
{
	int old_pos= this->repo.get_pos(photo);
	WeddingDress old_dress = this->repo.get_elem_from_pos(old_pos);

	this->repo.update(photo, size, price, quantity);

	int new_pos = this->repo.get_pos(photo);
	WeddingDress new_dress = this->repo.get_elem_from_pos(new_pos);

	UndoAction* act = new UndoUpdate{ old_dress,new_dress,this->repo };
	this->undoActions.push_back(act);
	UndoAction* act2 = new UndoUpdate{ new_dress,old_dress,this->repo };
	this->redoActions.push_back(act2);

	/*UndoAction* act = new UndoAdd{ this->repo.get_elem_from_pos(pos) ,this->repo };
	this->undoActions.push_back(act);
	UndoAction* act2 = new UndoRemove{ this->repo.get_elem_from_pos(pos),this->repo };
	this->redoActions.push_back(act2);*/

}
void Controller::undo()
{
	if (this->undoActions.size() > 0)
	{
		this->undoActions.at(this->undoActions.size() - 1)->do_undo();
		this->undoActions.pop_back();
	}
	
}
void Controller::redo()
{
	if (this->redoActions.size() > 0)
	{
		this->redoActions.at(this->redoActions.size() - 1)->do_undo();
		this->redoActions.pop_back();
	}
	
}
//returns the elems from the repo
Repository & Controller::get_repo()
{
	return this->repo;
}
//returns the elems from the basket
RepoForBasket & Controller::get_basket()
{
	return this->repoBasket;
}
std::vector<WeddingDress> Controller::sort_by_price()
{
	std::vector<WeddingDress> vect=this->repo.get_all();
	for (unsigned int i = 0; i < vect.size() - 1; i++)
	{
		for (unsigned int j = i + 1; j < vect.size(); j++)
		{
			if (vect.at(i).get_price() > vect.at(j).get_price())
			{
				std::swap(vect.at(i), vect.at(j));

			}
		}
	}
	return vect;
}
std::vector<WeddingDress> Controller::random_shuffle()
{
	std::vector<WeddingDress> vect = this->repo.get_all();
	std::random_shuffle(vect.begin(), vect.end());
	return vect;
}

//destructor
Controller::~Controller()
{
}




