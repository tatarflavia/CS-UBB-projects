from django.urls import path, re_path, include
from django.contrib import admin
from rest_framework import routers

import dressDjangoApp.views as dressApp
from dressDjangoApp import views

router = routers.DefaultRouter()
router.register(r'dresses', views.DressServerView)
# Wire up our API using automatic URL routing.
# Additionally, we include login URLs for the browsable API.
urlpatterns = [
    path('', include(router.urls)),
    path('api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]