import tensorflow as tf
from keras.models import Sequential
from keras.layers import Dense, Conv2D, Flatten, MaxPooling2D
from matplotlib import pyplot


#LOADING DATA

#loading the data for train and test: 60.000 training images of 28x28 pixels and 10.000 test images
#xInputImages: rgb codes representing handwritten digits.....yRealOutputs: digits from 0 to 9 depending on the photo
(xInputImagesTrain, yRealOutputsTrain), (xInputImagesTest, yRealOutputsTest) = tf.keras.datasets.mnist.load_data()


#PREPARING DATA: RESHAPE AND NORMALIZE THE IMAGES

# Reshaping the array to 4-dims so that it can work with the Keras API
xInputImagesTrain = xInputImagesTrain.reshape(xInputImagesTrain.shape[0], 28, 28, 1)
xInputImagesTest = xInputImagesTest.reshape(xInputImagesTest.shape[0], 28, 28, 1)
input_shape = (28, 28, 1) #shape of images 28X28 pixels

# Making sure that the values are float so that we can get decimal points after division
xInputImagesTrain = xInputImagesTrain.astype('float32')
xInputImagesTest = xInputImagesTest.astype('float32')
# Normalizing the RGB codes by dividing it to the max RGB value.

xInputImagesTrain /= 255
xInputImagesTest /= 255
print('x InputImages shape(now 4D array):', xInputImagesTrain.shape)
print('Number of images for training:', xInputImagesTrain.shape[0])
print('Number of images for testing:', xInputImagesTest.shape[0])


#BUILD THE CNN

#creating a sequential model and adding the layers: conv, pool, flatten, dense
model = Sequential()

#adding conv for filtering the images => 3D arrays from 4D arrays
model.add(Conv2D(15, kernel_size=(3,3), input_shape=input_shape, use_bias=False)) #filter_nr, filterMatrixSize, inputImageShape

#adding pooling to reduce sizes and complexity => 2D arrays from 3D
model.add(MaxPooling2D(pool_size=(2, 2)))  #poolMatrixShape

#adding flattenning before fully connected layers at the end => 1D arrays from 2D : for easy interpreting
model.add(Flatten())

#adding the final: set of fully connected layers for classification of the image
model.add(Dense(10,activation=tf.nn.softmax)) # 10 because we need 10 neurons for the output because 10 digits
												#softmax sets the 1D array to probabilities: how close is to be 1= activation function => probs = errors from the gradient descent alg

#OPTIMIZATION AND TRAINING

#give the model an optimization with a loss function: adam: stochastic gradient descent method
model.compile(optimizer='adam',loss='sparse_categorical_crossentropy',metrics=['accuracy'])
#start training
print("CNN is ready")
print( "Training starts...")
model.fit(x=xInputImagesTrain,y=yRealOutputsTrain, epochs=1)


#TESTING THE MODEL

print("Training is done")
print("Testing starts..")
result=model.evaluate(xInputImagesTest, yRealOutputsTest)
print("Testing is done!")

#PRINT THE RESULTS

print("The error is:"+str(round(result[0],2)))
print("The accuracy of the CNN is:"+str(round(result[1]*100,2))+"%")



for i in range(8):
	# define subplot
	pyplot.subplot(330 + 1 + i)
	# plot raw pixel data
	pyplot.imshow(xInputImagesTest[i].reshape(28,28), cmap=pyplot.get_cmap('gray'))
	pred = model.predict(xInputImagesTest[i].reshape(1, 28, 28, 1))
	print(pred.argmax())
# show the figure
pyplot.show()
#
# image_index = 4444
# pyplot.imshow(xInputImagesTest[image_index].reshape(28, 28),cmap='Greys')
# pred = model.predict(xInputImagesTest[image_index].reshape(1, 28, 28, 1))
# print(pred.argmax())
# #
# #
# pyplot.show()


