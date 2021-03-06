# Generated by Django 3.1.5 on 2021-01-08 17:28

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Dress',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('dressCode', models.CharField(max_length=255)),
                ('dressName', models.CharField(max_length=255)),
                ('dressSize', models.PositiveIntegerField()),
                ('dressPrice', models.PositiveBigIntegerField()),
                ('dressColour', models.CharField(max_length=255)),
                ('dressQuantity', models.PositiveIntegerField()),
                ('tailoringTime', models.CharField(max_length=255)),
                ('dressDescription', models.TextField()),
                ('dressPhoto', models.CharField(max_length=255)),
            ],
        ),
    ]
