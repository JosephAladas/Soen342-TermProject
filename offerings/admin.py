# admin.py
from django.contrib import admin
from .models import Offering, Lesson

admin.site.register(Offering)
admin.site.register(Lesson)
