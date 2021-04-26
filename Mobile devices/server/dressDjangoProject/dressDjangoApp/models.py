from django.db import models


# Create your models here.


class Dress(models.Model):
    dressCode = models.CharField(max_length=255)
    dressName = models.CharField(max_length=255)
    dressSize = models.PositiveIntegerField()
    dressPrice = models.PositiveBigIntegerField()
    dressColour = models.CharField(max_length=255)
    dressQuantity = models.PositiveIntegerField()
    tailoringTime = models.CharField(max_length=255)
    dressDescription = models.TextField()
    dressPhoto = models.CharField(max_length=255)

    def __str__(self):
        return self.dressCode+"\n"+self.dressName