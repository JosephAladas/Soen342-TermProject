from django.shortcuts import render

# Use the base.html template as the homepage
def home(request):
    return render(request, 'homepageapp/base.html')  # Rendering base.html for the homepage
