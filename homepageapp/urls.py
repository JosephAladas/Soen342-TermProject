from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='home'),  # Render the base.html template at the root URL
]
