from rest_framework.decorators import action
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status, viewsets
from rest_framework.generics import ListAPIView
from .models import Dress
from .serializer import DressSerializer


# Create your views here.
#
# class AllDress(ListAPIView):
#     # inherit rom this class to list all entries
#     # also use it to create a new entry with post fct
#     queryset = Dress.objects.all()
#     serializer_class = DressSerializer
#
#     def post(self, request, format=None):
#         serializer = DressSerializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data, status=status.HTTP_201_CREATED)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
#
# class DressView(APIView):
#     # inherit from this class to perform CRUD
#     # only read and delete for now
#     def get(self, request, pk, format=None):
#         try:
#             tech = Dress.objects.get(pk=pk)
#             serializer = DressSerializer(tech)
#             return Response(serializer.data)
#         except:
#             return Response(status=status.HTTP_404_NOT_FOUND)
#
#     def delete(self, request, pk, format=None):
#         tech = Dress.objects.get(pk=pk)
#         tech.delete()
#         return Response(status=status.HTTP_200_OK)

class DressServerView(viewsets.ModelViewSet):
    queryset = Dress.objects.all()
    serializer_class = DressSerializer

    def create(self, request, *args, **kwargs):
        # this will create the list of dresses here
        serializer = self.get_serializer(data=request.data, many=isinstance(request.data, list))
        serializer.is_valid(raise_exception=True)
        self.perform_create(serializer)
        headers = self.get_success_headers(serializer.data)

        return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)
