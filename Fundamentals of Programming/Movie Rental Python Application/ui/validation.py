class valid(object):


    def validAdd(self,command):
        if len(command)!=6:
            if len(command)!=4:
                raise ValueError("Invalid number of params!")
        return True


    def validPrint(self,command):
        if len(command)!=2:
            raise ValueError("Invalid number of params!")
        if command[1]=='movie' or command[1]=='client' or command[1]=='rental':
            return True



    def validUpdate(self,command):
        if len(command)!=8:
            if len(command)!=6:
                raise ValueError("Not enough params!")
            elif command[3] == 'with':
                return True
        elif command[3]=='with':
            return True



    def validRemove(self,command):
        if len(command)!=3:
            raise ValueError("Not enough params!")
        if command[1]=='client' or command[1]=='movie':
            return True



