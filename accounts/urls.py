
from django.urls import path
from . import views

urlpatterns = [
    path('register/', views.register, name='register'),
    # Add login, logout URLs here if necessary
]
