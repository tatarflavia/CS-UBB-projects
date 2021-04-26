from rest_framework import serializers
from .models import Dress


class DressSerializer(serializers.ModelSerializer):
    class Meta:
        model = Dress
        fields = ('id', 'dressCode', 'dressName', 'dressSize',
                  'dressPrice', 'dressColour',
                  'dressQuantity', 'tailoringTime', 'dressDescription',
                  'dressPhoto')
        # fields = '__all__'
