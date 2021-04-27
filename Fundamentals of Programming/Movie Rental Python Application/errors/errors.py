class RepoError(Exception):
    '''
    Exception class for repository errors
    '''
    def __init__(self,message):
        self.__message=message
    def __str__(self):
        '''
        Function that helps in writing the repo errors
        '''
        return self.__message


class ValidError(Exception):
    '''
        Exception class for validation of the entities errors
    '''
    def __init__(self,message):
        self.__message=message
    def __str__(self):
        '''
        Function that helps in writing the validation errors
        '''
        return self.__message
