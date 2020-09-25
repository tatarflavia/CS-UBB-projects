from keras.datasets import mnist
from matplotlib import pyplot
# load dataset
(trainX, trainy), (testX, testy) = mnist.load_data()
# summarize loaded dataset
print('Train: X=%s, y=%s' % (trainX.shape, trainy.shape))
print('Test: X=%s, y=%s' % (testX.shape, testy.shape))
# plot first few images
for i in range(9):
	# define subplot
	pyplot.subplot(330 + 1 + i)
	# plot raw pixel data
	pyplot.imshow(trainX[i], cmap=pyplot.get_cmap('gray'))
	plt.imshow(xInputImagesTest[i].reshape(28, 28), cmap='Greys')
	pred = model.predict(xInputImagesTest[i].reshape(1, 28, 28, 1))
	print(pred.argmax())
# show the figure
pyplot.show()