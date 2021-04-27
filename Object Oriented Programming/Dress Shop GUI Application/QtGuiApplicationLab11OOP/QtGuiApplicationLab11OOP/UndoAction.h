#pragma once
#include "WeddingDress.h"
#include "Repository.h"
class UndoAction
{
public:
	UndoAction();
	virtual void do_undo() = 0;
	virtual ~UndoAction();
};



class UndoAdd : public UndoAction
{
private:
	WeddingDress newDress;
	Repository& repo;
public:
	UndoAdd(WeddingDress newDress, Repository& repo);
	void do_undo() override;
	~UndoAdd();
};


class UndoRemove : public UndoAction
{
private:
	WeddingDress oldDress;
	Repository& repo;
public:
	UndoRemove(WeddingDress oldDress, Repository& repo);
	void do_undo() override;
	~UndoRemove();
};

class UndoUpdate : public UndoAction
{
private:
	WeddingDress oldDress;
	WeddingDress newDress;
	Repository& repo;
public:
	UndoUpdate(WeddingDress oldDress, WeddingDress newDress, Repository& repo);
	void do_undo() override;
	~UndoUpdate();
};
