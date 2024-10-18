
from django.urls import path
from . import views

urlpatterns = [
    path('', views.offerings_list, name='offerings'),
    path('instructor/', views.instructor_offerings, name='instructor_offerings'),
    path('<int:lesson_id>/register/', views.register_for_lesson, name='register_for_lesson'),
]
