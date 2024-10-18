from django.shortcuts import redirect, render
from .models import Offering, Lesson
from django.contrib.auth.decorators import login_required

def offerings_list(request):
    offerings = Offering.objects.all()
    
    if request.headers.get('Hx-Request'):  # HTMX request handling
        return render(request, 'offerings/offerings_list_partial.html', {'offerings': offerings})
    return render(request, 'offerings/offerings_list.html')  # Full-page load for non-HTMX request

def instructor_offerings(request):
    if request.user.is_authenticated and request.user.user_type == 'instructor':
        offerings = Offering.objects.filter(lesson__isnull=True)
        if request.headers.get('Hx-Request'):  # HTMX request handling
            return render(request, 'offerings/instructor_offerings_partial.html', {'offerings': offerings})
        return render(request, 'offerings/instructor_offerings.html', {'offerings': offerings})
    return redirect('home')

@login_required
def register_for_lesson(request, lesson_id):
    lesson = Lesson.objects.get(id=lesson_id)
    if request.user.user_type == 'client':
        # Logic for client registration
        pass
    return redirect('offerings')
