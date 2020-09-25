import numpy as np
from Domain.State import State

class EvolutionaryAlgorithm(object):
    def __init__(self , n,populationSize,trials,mutation,crossover):
        self.population = [] #a list of states (a list of people)
        self.n = n  #dimensions for the matrix
        self.mutation = mutation  # a number between 0.3-0.7
        self.mutationFactor = (n*(n-1))*2  # a number big enough for the fitness func as to not return neg nbs
        self.current_generation = 0
        self.trials=trials
        self.nrOfStates=populationSize
        self.crossoverProbability=crossover
        self.standardDeviation=0
        self.mean=0
        for i in range(self.nrOfStates):
            self.population.append(self.getInitialIndivid()) # a list of state objects
        self.population.sort(key=self.keyGeneratorForSorting,reverse=True)


    def getInitialIndivid(self):
        #gets an individ aka a matrix (everytime a different one)
        state = []
        for i in range(2*self.n): #we will have 2*n permutations
            permutation = np.random.permutation(self.n)
            for j in range(len(permutation)):
                #we just add 1 to be like in the example
                permutation[j]+=1
            permutation=tuple(permutation)
            state.append(permutation)
        individ=State(state)
        return individ

    def fitnessFunction(self,state):
        #ret a number: the bigger, the closer you are to the solution
        #aka it checks the permutations in the state and gives a result based on how close to the sol is this state
        state_vals=state.get_vals()
        version1=0 #it would be perfect to remain 0 for it to be a sol

        #we check the lines first
        for i in range(self.n):
            l=[]
            for j in range(self.n):
                l.append(state_vals[i][j]) #put the permutations from lines in l
            #get how many perms are the same: if 0 => all unique => very close to sol
            version1+=len(l)-len(set(l)) #set holds only unique perm from l

        #we check the columns second
        version2=0
        for i in range(self.n):
            l=[]
            for j in range(self.n,self.n*2):
                l.append(state_vals[j][i]) #perms from columns in l
            version2+=len(l)-len(set(l))

        #lastly we check that all pairs of permutations are different from one another no matter the position
        version3=0
        l=[]
        for i in range(self.n):
            j=self.n+i #this is how we get the second part of the pair (i,j) from state
            for k in range(self.n):
                l.append((state_vals[i][k],state_vals[j][k])) #here we add pairs of permutations from the state
        version3+=len(l)-len(set(l))

        #now return a number
        return self.mutationFactor-version3-version2-version1


    def keyGeneratorForSorting(self,state):
        #gets a state and returns the fitness of it (we will use these for the sorting criteria when making a new population)
        return self.fitnessFunction(state)

    def mutate(self,state):
        #gets a state aka a child and it gives it a natural mutation (completely random)
        #basically we change 2 permutations for a random first pos from a pair and a random second pos from a pair
        poz1=np.random.randint(0,self.n)
        poz2=np.random.randint(self.n,self.n*2)
        permutation1=np.random.permutation(self.n)
        permutation2=np.random.permutation(self.n)
        for i in range(self.n):
            #change them according to the example of the teacher
            permutation1[i]+=1
            permutation2[i]+=1
        state_vals=state.get_vals()
        state_vals[poz1]=permutation1
        state_vals[poz2]=permutation2
        state.set_vals(state_vals)

        return state

    def crossPermutations(self,perm1,perm2):
        #gets 2 permutations and returns a new one after crossing them
        #basically we first take 2 indexes from the first perm (including middle=n/2),put it in the res
            #then take for the remaining positions from the second perm numbers, only take them from the end
                                                #in order to get the desired chaos
        n=len(perm1) #len of the perm
        i1=np.random.randint(0,n//2)  #random pos from the first perm
        i2=i1+n//2 #second pos from the perm (as to contain the middle n/2)
        child=[0]*self.n #preparing the child
        for i in range(i1,i2):
            child[i]=perm1[i] #put the vals first from the perm1
        index=i2
        #now put vals from perm2 from the 0,i1 and i2,n ranges
        for i in range(i2,n):
            if child[i]==0:
                while perm2[index%n] in  child:  #while we have that in child, we look for an unused elem
                    index+=1
                child[i]=perm2[index%n]
        for i in range(i1):
            if child[i]==0:
                while perm2[index%n] in  child:  #while we have that in child, we look for an unused elem
                    index+=1
                child[i] = perm2[index % n]

        return child


    def crossOver2Parents(self,parent1,parent2):
        #we get 2 states and by crossing all permutations we get a child that we return
        valsFromFirstParent=parent1.get_vals()
        valsFromSecondParent = parent2.get_vals()
        child_vals=[]
        ran=np.random.random()
        if self.crossoverProbability>ran:
            #we cross all permutations and add the result to the kid state
            for i in range(len(valsFromFirstParent)):
                child_vals.append(self.crossPermutations(valsFromFirstParent[i],valsFromSecondParent[i]))
        child=State(child_vals)
        return child

    def createNewPopulation(self):
        #we return a new population: we change the self.pop and return it
        length=len(self.population)
        #first we cut the old and bad states from the pop, and as the first ones are the best ones, we keep them
        if length>self.nrOfStates:
            length=self.nrOfStates
        else:
            length=length//2
        #actual cut of the pop:
        population=self.population[0:length]
        nextPop=[]
        #create the new pop:
        for i in range(length):
            for j in range(length):
                #we must crossover all the people and give the children to the next gen
                if i!=j:
                    kid=self.crossOver2Parents(population[i],population[j])
                    if kid.get_vals()!=[]:
                        #we randomly give it or not a mutation
                        if(np.random.random()<self.mutation):
                            kid=self.mutate(kid)
                        nextPop.append(kid)
        #we sort it according to the fitnesse function: the bigger the best, they stay in front of the pop
        nextPop.sort(key=self.keyGeneratorForSorting,reverse=True)
        return nextPop

    def mainForEvolutionaryAlg(self):
        #here we have a number of trials in which we try to find a solution by forming new and new generations
        while self.current_generation!=self.trials: #we have 1000 trials => 1000 generations
            if self.fitnessFunction(self.population[0])==self.mutationFactor: #we found our sol if the best state is ok in the fitness func
                print("solution:\n"+str(self.population[0]))
                return self.population[0]
            self.population=self.createNewPopulation()
            #compute mean and deviation
            res=[]
            for state in self.population:
                res.append(self.fitnessFunction(state))
            self.current_generation+=1
        print("no sol,but the closest is:\n")

    def validateTest(self):
        #1000 trials, for each population we do fitness and then at the end findout the mean and std deviation
        res=[]
        while self.current_generation!=self.trials: #we have 1000 trials => 1000 generations

            self.population=self.createNewPopulation()
            #compute mean and deviation
            for state in self.population:
                res.append(self.fitnessFunction(state))
            self.current_generation+=1
            if self.fitnessFunction(self.population[0]) == self.mutationFactor:
                break
        return (np.std(res),np.mean(res))







