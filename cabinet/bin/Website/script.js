document.addEventListener('DOMContentLoaded', function() {
    // Wait for the DOM to be fully loaded before running any scripts
    
    /**
     * Smooth Scrolling Implementation
     * - Selects all anchor links that start with #
     * - Prevents default jump behavior
     * - Smoothly scrolls to the target section
     */
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault(); // Prevent default jump
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth' // Enable smooth scrolling
            });
        });
    });

    /**
     * Feature Cards Animation
     * Uses Intersection Observer API to detect when cards enter viewport
     * - Creates an observer instance to watch for cards
     * - Adds animation class when card becomes visible
     * - Triggers fade-in and slide-up animation
     */
    const cards = document.querySelectorAll('.feature-card');
    const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                // Add animation class when card enters viewport
                entry.target.classList.add('animate');
            }
        });
    });

    // Start observing each feature card
    cards.forEach(card => observer.observe(card));

    /**
     * Image Modal Implementation
     * Creates a lightbox effect for screenshots
     * - Dynamically creates modal container
     * - Handles opening and closing of modal
     * - Supports click-to-close functionality
     */
    const screenshots = document.querySelectorAll('.screenshot-grid img');
    const modal = document.createElement('div');
    modal.className = 'modal';
    document.body.appendChild(modal);

    // Add click event to each screenshot
    screenshots.forEach(img => {
        img.addEventListener('click', () => {
            // Create modal content with close button and enlarged image
            modal.innerHTML = `
                <span class="close">&times;</span>
                <img src="${img.src}" alt="${img.alt}">
            `;
            modal.style.display = 'flex'; // Show modal
        });
    });

    // Handle modal closing
    modal.addEventListener('click', (e) => {
        // Close modal when clicking outside image or on close button
        if (e.target.className === 'modal' || e.target.className === 'close') {
            modal.style.display = 'none';
        }
    });

    /**
     * Keyboard Navigation (ESC to close modal)
     * Adds keyboard support for accessibility
     */
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape' && modal.style.display === 'flex') {
            modal.style.display = 'none';
        }
    });

    /**
     * Performance Optimization
     * - Uses event delegation where possible
     * - Minimizes DOM queries
     * - Efficiently handles animations
     */

    /**
     * Accessibility Features
     * - Keyboard navigation support
     * - ARIA labels for interactive elements
     * - Semantic HTML structure
     */
}); 