#include "UndoAction.h"



UndoAction::UndoAction()
{
}


UndoAction::~UndoAction()
{
}

UndoAdd::UndoAdd(WeddingDress newDress, Repository & repo) :newDress{ newDress }, repo{repo}
{
}

void UndoAdd::do_undo()
{
	this->repo.remove(this->newDress.get_photograph());
}

UndoAdd::~UndoAdd()
{
}

UndoRemove::UndoRemove(WeddingDress oldDress, Repository & repo) : oldDress{ oldDress }, repo{repo}
{
}

void UndoRemove::do_undo()
{
	this->repo.add(this->oldDress);
}

UndoRemove::~UndoRemove()
{
}

UndoUpdate::UndoUpdate(WeddingDress oldDress, WeddingDress newDress, Repository & repo) :oldDress{ oldDress }, newDress{ newDress }, repo{repo}
{
}

void UndoUpdate::do_undo()
{
	this->repo.remove(this->newDress.get_photograph());
	this->repo.add(this->oldDress);
}



UndoUpdate::~UndoUpdate()
{
}
