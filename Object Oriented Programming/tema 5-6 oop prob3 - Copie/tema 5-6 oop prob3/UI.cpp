#include "UI.h"
#include <iostream>
#include <string>
#include <Windows.h>
#include <fstream>

using namespace std;

UI::UI(Controller & cont) :ctlr{cont}
{
}

void UI::first_menu()
{
	std::cout << "Please choose your occupation:\n";
	std::cout << "1.admnistrator\n";
	std::cout << "2.client\n";
	std::cout << "0.exit(if you want to leave the application)\n";
}

void UI::user_menu()
{
	std::cout << "This is the menu of commands:\n";
	std::cout << "1.see the dresses\n";
	std::cout << "2.see the shopping basket\n";
	std::cout << "0.exit(if you want to leave the store)\n";
}

void UI::administrator_menu()
{
	std::cout << "This is the menu of commands for administration:\n";
	std::cout << "1.add a new wedding dress collection\n";
	std::cout << "2.delete a wedding dress collection\n";
	std::cout << "3.update a wedding dress collection\n";
	std::cout << "4.print available dresses\n";
	std::cout << "5.sort ascendingly by size\n";
	std::cout << "6.sort descendingly by price\n";
	std::cout << "0.exit(if you want to exit the application)\n";
}

void UI::administrator_run()
{
	std::cout << "Welcome, administrator!\n";
	
	while (1) 
	{
		this->administrator_menu();
		std::cout << "Please give a command to start working:";
		int input = 0;
		std::cin >> input;
		if (input == 0)
		{
			std::cout << "You chose to leave administrator mode!Bye!Check the database for changes!\n";

			ofstream fout("file.txt");
			int i = 0;

			for (i = 0; i < this->ctlr.size(); i++)
			{
				fout << this->ctlr.get_repo().get_elem_from_pos(i)->get_size() << " " << this->ctlr.get_repo().get_elem_from_pos(i)->get_colour() << " " << this->ctlr.get_repo().get_elem_from_pos(i)->get_price() << " " << this->ctlr.get_repo().get_elem_from_pos(i)->get_quantity() << " " << this->ctlr.get_repo().get_elem_from_pos(i)->get_photograph() << "\n";
				fout << "\n";
				ShellExecuteA(NULL, NULL, "chrome.exe", this->ctlr.get_repo().get_elem_from_pos(i)->get_photograph().c_str(), NULL, SW_SHOWMAXIMIZED);
			}
			return;
		}
		else if (input == 1) 
		{
			int size = 0;
			std::string colour;
			float price = 0;
			int quantity = 0;
			std::string photograph;
			std::cout << "Please give the size for the dress:";
			std::cin >> size;
			std::cout << "Please give the colour for the dress:";
			std::cin >> colour;
			std::cout << "Please give the price for the dress:";
			std::cin >> price;
			std::cout << "Please give the quantity for the dress:";
			std::cin >> quantity;
			std::cout << "Please give the link to the photo of the dress:";
			std::cin>>photograph;
			if (this->ctlr.get_repo().search_elem_with_link(photograph))
				std::cout << "The dress exists already!\n";
			else
			{
				this->ctlr.add(size, colour, price, quantity, photograph);
				std::cout << "The dress has been added.\n";
			}

		}
		else if (input == 2) 
		{
			std::string link;
			std::cout << "Please enter the link for the dress you want to delete:";
			std::cin >> link;
			if (not(this->ctlr.get_repo().search_elem_with_link(link)))
				std::cout << "The dress is not in the database!\n";
			else 
			{
				this->ctlr.remove(link);
				std::cout << "The dress has been deleted.\n";
			}
		}
		else if (input == 3)
		{
			std::string pht;
			std::cout << "Please enter the link for the dress you want to update:";
			std::cin >> pht;
			int marime = 0;
			float pret = 0;
			int cantitate = 0;
			std::cout << "Please give the new size for the dress:";
			std::cin >> marime;
			std::cout << "Please give the new price for the dress:";
			std::cin >> pret;
			std::cout << "Please give the new quantity for the dress:";
			std::cin >> cantitate;
			if (not(this->ctlr.get_repo().search_elem_with_link(pht)))
				std::cout << "The dress is not in the database!\n";
			else
			{
				this->ctlr.update(pht, marime, pret, cantitate);
				std::cout << "The dress has been updated.\n";
			}
		}
		else if (input == 4)
		{
			std::cout << "The available collections are:\n";
			int i = 0;

			for (i = 0; i < this->ctlr.size(); i++)
			{
				std::cout << "The dress costs " << this->ctlr.get_repo().get_elem_from_pos(i)->get_price() << " ,has the size " << this->ctlr.get_repo().get_elem_from_pos(i)->get_size() << ", the colour " << this->ctlr.get_repo().get_elem_from_pos(i)->get_colour() << ", the quantity " << this->ctlr.get_repo().get_elem_from_pos(i)->get_quantity() << " and the photo: \n";
				ShellExecuteA(NULL, NULL, "chrome.exe", this->ctlr.get_repo().get_elem_from_pos(i)->get_photograph().c_str(), NULL, SW_SHOWMAXIMIZED);
			}
		}
		else { std::cout << "Invalid command! Please try again!\n"; }


	}
}

void UI::user_run()
{
	std::cout << "Hello and welcome to Proper Wedding Dresses store! Would you like to see the dresses?\n";
	while (1)
	{
		this->user_menu();
		std::cout << "Please choose a command:";
		int in = 0;
		std::cin >> in;
		if (in == 0)
		{
			std::cout << "You chose to exit!Come back soon!\n";
			return;
		}
		else if (in == 1)
		{
			//choose the size, show the list one by one and the total sum
			std::cout << "Would you like to see all the dresses or choose a size? 1 for all the dresses or a number for the size chosen(36,38,40,42,44).\n";
			std::cout << "Please give your choice:";
			int n = 10;
			std::cin >> n;
			this->user_shop_run(n);
			
		}
		else if (in == 2)
		{
			//see the shopping basket and the total sum
			
			if (this->ctlr.size_of_basket() == 0)
				std::cout << "The shopping basket is empty! Please go back and buy something!\n";
			else {std::cout << "The price is:" << this->ctlr.get_price() << " and the basket contains the dresses:\n";
				int i = 0;
			for (i = 0; i < this->ctlr.size_of_basket(); i++)
			{
				std::cout << "The dress costs " << this->ctlr.get_basket().get_elem_from_pos(i)->get_price() << " ,has the size " << this->ctlr.get_basket().get_elem_from_pos(i)->get_size() << ", the colour " << this->ctlr.get_basket().get_elem_from_pos(i)->get_colour() << ", the quantity " << this->ctlr.get_basket().get_elem_from_pos(i)->get_quantity() << " and the photo: \n";
				ShellExecuteA(NULL, NULL, "chrome.exe", this->ctlr.get_basket().get_elem_from_pos(i)->get_photograph().c_str(), NULL, SW_SHOWMAXIMIZED);
			}}
			
		}
		else { std::cout << "Wrong command! Please try again!\n"; }
	}
}



int UI::first_run()
{
	std::cout << "Welcome to 'Proper Wedding Dresses' store!\n";
	while (1) 
	{
		this->first_menu();
		int n = 0;
		std::cout << "If you would like to exit please enter '0'.\n";
		std::cout << "Please give your answer:";
		std::cin >> n;
		if (n == 1)
		{
			this->administrator_run();
		}
		else if (n == 2)
		{
			this->user_run();
		}
		else if (n == 0) {
			std::cout << "You chose to exit the application!Goodbye!\n";
			return 0; }
		else { std::cout << "Invalid command!Please try again!\n"; }
	}
}

int UI::user_shop_run(int size)
{
	int capacity = this->ctlr.size();
	int m = 0;
	while (1)
	{
		if (size == 1) 
		{
			std::cout << "Next dress is:\n";
			std::cout << "The dress costs " << this->ctlr.get_repo().get_elem_from_pos(m)->get_price() << " ,has the size " << this->ctlr.get_repo().get_elem_from_pos(m)->get_size() << ", the colour " << this->ctlr.get_repo().get_elem_from_pos(m)->get_colour() << ", the quantity " << this->ctlr.get_repo().get_elem_from_pos(m)->get_quantity() << " and the photo: \n";
			ShellExecuteA(NULL, NULL, "chrome.exe", this->ctlr.get_repo().get_elem_from_pos(m)->get_photograph().c_str(), NULL, SW_SHOWMAXIMIZED);
			std::cout << "Would you like to add this to the shopping cart?Choose Y or N:";
			std::string adding;
			std::cin >> adding;
			if (adding.find("Y") == 0)
			{
				this->ctlr.addToBasket(*this->ctlr.get_repo().get_elem_from_pos(m));
				std::cout << "Current price is:" << this->ctlr.get_price() << "\n";
				this->ctlr.update(this->ctlr.get_repo().get_elem_from_pos(m)->get_photograph(), this->ctlr.get_repo().get_elem_from_pos(m)->get_size(), this->ctlr.get_repo().get_elem_from_pos(m)->get_price(), this->ctlr.get_repo().get_elem_from_pos(m)->get_quantity() - 1);
				if (this->ctlr.get_repo().get_elem_from_pos(m)->get_quantity() == 0)
				{
					this->ctlr.remove(this->ctlr.get_repo().get_elem_from_pos(m)->get_photograph());
					capacity = capacity - 1;
				}
			}
			std::cout << "Would you like to see the next or go back?Choose 'next' or 'back':";
			std::string next;
			std::cin >> next;
			if (next.find("back")==0)
			{
				return 0;
			}
			m++;
			if (m >= capacity)
				m = 0;
			
		}
		else 
		{
			if (this->ctlr.get_number_of_dresses_after_size(size) != 0) {
			while (this->ctlr.get_repo().get_elem_from_pos(m)->get_size() != size)
			{
				m++;
				if (m >= capacity)
					m = 0;
			}
			std::cout << "Next dress is:\n";
			std::cout << "The dress costs " << this->ctlr.get_repo().get_elem_from_pos(m)->get_price() << " ,has the size " << this->ctlr.get_repo().get_elem_from_pos(m)->get_size() << ", the colour " << this->ctlr.get_repo().get_elem_from_pos(m)->get_colour() << ", the quantity " << this->ctlr.get_repo().get_elem_from_pos(m)->get_quantity() << " and the photo: \n";
			ShellExecuteA(NULL, NULL, "chrome.exe", this->ctlr.get_repo().get_elem_from_pos(m)->get_photograph().c_str(), NULL, SW_SHOWMAXIMIZED);
			std::cout << "Would you like to add this to the shopping cart?Choose Y or N:";
			std::string adding;
			std::cin >> adding;
			if (adding.find("Y") == 0)
			{
				this->ctlr.addToBasket(*this->ctlr.get_repo().get_elem_from_pos(m));
				std::cout << "Current price is:" << this->ctlr.get_price() << "\n";
				this->ctlr.update(this->ctlr.get_repo().get_elem_from_pos(m)->get_photograph(), this->ctlr.get_repo().get_elem_from_pos(m)->get_size(), this->ctlr.get_repo().get_elem_from_pos(m)->get_price(), this->ctlr.get_repo().get_elem_from_pos(m)->get_quantity() - 1);
				if (this->ctlr.get_repo().get_elem_from_pos(m)->get_quantity() == 0)
				{
					this->ctlr.remove(this->ctlr.get_repo().get_elem_from_pos(m)->get_photograph());
					capacity = capacity - 1;
				}
			}
			std::cout << "Would you like to see the next or go back?Choose 'next' or 'back':";
			std::string next;
			std::cin >> next;
			if (next.find("back") == 0)
			{
				return 0;
			}
			m++;
			if (m >= capacity)
				m =0;}
			else {
				std::cout << "There are no such dresses in our store. We are sorry! Maybe choose another size?\n";
				return 0;
			}
			
		}
		
		
	}
}









UI::~UI()
{
}
